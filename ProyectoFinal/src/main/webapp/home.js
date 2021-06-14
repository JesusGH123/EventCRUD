import {prevent_html_injection} from './commonjs/code_injection_avoidance.js';

document.addEventListener("DOMContentLoaded",()=> {
    let get_single_event = async (event_id)=>{
        //console.log(`event_id: ${event_id}`)
        let formData = new FormData();
        formData.append("type", "single");
        formData.append("event_id", event_id);
        /*for (var pair of formData.entries()) {
            console.log(pair[0]+ ', ' + pair[1]);
        }*/
        return await (await fetch('event', {
            method: 'POST'
            , body: formData
        })).json();
        //console.log(`event: ${event}`)
    }
    let save_update = async (event) => {
        console.log(event.target.getAttribute('data-id'))
        let event_id =  event.target.getAttribute('data-id');
        console.log('save_update'+event.target)
        try {
            //return
            //console.log(`event:  ${event}`)
            //return
            //console.log(event_id)
            //console.log(`event_id: ${event_id}`)
            let event = undefined
            if (event_id) {
                event = await get_single_event(event_id)

                //console.log(`event: ${event}`)
            }

            let begin_datetime=undefined
            let begin_datetime_tokens = undefined
            let begin_date = undefined
            let begin_time = undefined
            let end_datetime = undefined
            let end_datetime_tokens = undefined
            let end_date = undefined
            let end_time = undefined
            if(event_id) {
                //console.log(event.begin_date)
                begin_datetime = new Date(event.begin_date).toISOString().slice(0, 19).replace('T', ' ')
                begin_datetime_tokens = begin_datetime.split(' ')
                begin_date = begin_datetime_tokens[0]
                begin_time = event.begin_date.split(',')[2]
                //console.log(begin_time.length)
                if(begin_time.length===11)
                    begin_time = '0'+begin_time.substr(1)
                end_datetime = new Date(event.end_date).toISOString().slice(0, 19).replace('T', ' ')
                end_datetime_tokens = end_datetime.split(' ')
                end_date = end_datetime_tokens[0]
                end_time = event.end_date.split(',')[2]
                if(end_time.length === 11)
                    end_time='0'+end_time.substr(1)
                //console.log(begin_datetime)
                //console.log(begin_time)
            }
            if(event)
                console.log('title of retrieved event for save_update: ',event.title)

            let result = await Swal.fire({
                title: 'Evento',
                html:
                    '<form id="event_form" action="file" enctype="multipart/form-data">' +
                    `<label for="eventName" >Nombre del evento</label>` +
                    `<input id="eventName" name="eventName" ${event ? `value="${prevent_html_injection(event.title)}"` : ""} type="text"/>` +
                    '<label for "eventDescription">Descripción del evento </label>' +
                    `<input id="eventDescription" name="eventDescription" ${event ? `value="${prevent_html_injection(event.description)}"`:""} type="textArea"/>` +
                    '<label for="eventCategory">Categoría</label>' +
                    `<select name="eventCategory" id="eventCategory">` +
                    `<option value="Social" ${event&&event.category === "Social" ? "selected='selected'":""}>Social</option>` +
                    `<option value="Cultural"  ${event&&event.category === "Cultural" ? "selected='selected'":""}>Cultural</option>` +
                    `<option value="Deportivo"  ${event&&event.category === "Deportivo" ? "selected='selected'":""}>Deportivo</option>` +
                    `<option value="Artístico"  ${event&&event.category === "Artistico" ? "selected='selected'":""}>Artístico</option>` +
                    `<option value="Recreativo"  ${event&&event.category === "Recreativo" ? "selected='selected'":""}>Recreativo</option>` +
                    '</select>' +
                    '<div><label for="eventStartDay">Fecha de inicio evento</label>' +
                    `<input type="date" id="eventStartDay" name="eventStartDay" ${event ? `value=${begin_date}`:""} min="2021-06-12" max="2022-06-12"></div>` +
                    '<div><label for="eventStartHour">Hora inicio del evento</label>' +
                    `<input id="eventStartHour" name="eventStartHour" type="time" ${event ? `value=${begin_time}`:""}></div>` +
                    '<div><label for="eventEndDay">Fecha de cierre del evento</label>' +
                    `<input type="date" id="eventEndDay" name="eventEndDay" ${event? `value=${end_date}`:""} min="2021-06-12" max="2022-06-12"></div>` +
                    '<div><label for="eventEndHour">Hora de cierre del evento</label>' +
                    `<input id="eventEndHour" type="time" name="eventEndHour" ${event?`value=${end_time}`:""}></div>` +
                    '<label for="eventPrice">Precio del evento</label>' +
                    `<input id="eventPrice" type="number" name="eventPrice" ${event ?`value="${event.price}"`:""}>`
                    /*'<div><label for="eventImage">Imagen de evento</label>' +
                    '<input id="eventImage" name="eventImage" type="file"></div>'*/
                    + '</form>',

                showCancelButton: true
            });
            if(result.isConfirmed) {
                console.log(`event_id:${event_id}`)
                ///console.log(`event_start: ${}`)
                let start_date_str = document.getElementById("eventStartDay").value+" "+document.getElementById("eventStartHour").value
                let start_date_in = new Date(start_date_str)
                let end_date_str = document.getElementById("eventEndDay").value+" "+document.getElementById("eventEndHour").value
                let end_date_in = new Date(end_date_str);
                console.log('span dates str',start_date_str,end_date_str)
                console.log(`in span dates: ${start_date_in} ${end_date_in}`)
                let description = document.getElementById("eventDescription").value
                let title = document.getElementById("eventName").value
                let price = document.getElementById("eventPrice").value
                console.log('description',description,'title',title)
                let date_regex = /^\d{4}-\d{2}-\d{2} \d{2}(:\d{2}(:\d{2})?)?$/;
                //console.log('djlk')
                //console.log('first date regex result:',date_regex.test(start_date_str),'second data regex result:',date_regex.test(end_date_str))
                if(
                    !/^\d+(\.\d{1,2})?$/.test(price) ||
                    !date_regex.test(start_date_str) || !date_regex.test(end_date_str)
                    || !start_date_in || !end_date_in ||
                    start_date_in > end_date_in  || start_date_in <= new Date()
                    || !description || /^\s*$/.test(description)
                    || !title || /^\s*$/.test(title)
                ) //improve validation
                {
                    Swal.fire('Llena todos los campos de la manera correcta', '', 'error')
                }else {
                    let formData = new FormData(document.getElementById("event_form"));
                    let method = !event_id ? 'POST' : 'PUT';
                    console.log(`method: ${method}`);
                    if (event_id)
                        formData.append("event_id", event_id);
                    let post_result = await fetch('event', {
                        method: method
                        , body: formData
                    });
                    let response = await post_result.json();
                    let confirmation_result = await Swal.fire(response.message, '', 'success')
                    location.reload()
                }
            }
            //console.log(formData)
            //console.log(formData.get('eventPrice'))

        } catch (error) {
            console.log(error)
        }
    }
    let edit_buttons = document.querySelectorAll(".fa-edit")
    if(edit_buttons) {
        edit_buttons.forEach(element => {
            element.addEventListener("click", save_update)
        });
    }
    let more_information_buttons = document.querySelectorAll(".green-block")
    if(more_information_buttons){
        more_information_buttons.forEach(element=>{
            let event_id = element.getAttribute('data-id')
            //console.log('more_information event_id',event_id)
            //console.log('good')
            let more_info_last_event_listener = undefined
            let update_data = async () => {
                if (event_id) {
                    let event = await get_single_event(event_id)
                    //console.log(`interval ${event}`)
                    //console.log(event.title)
                    //console.log(element)
                    //console.log(document.querySelector(`#title_${event.event_id}`))
                    document.querySelector(`#title_${event.event_id}`).innerHTML = event.title
                    document.querySelector(`#category_${event.event_id}`).innerHTML = event.category
                    //console.log(document.querySelector(`#dates_${event.event_id}`))
                    document.querySelector(`#dates_${event.event_id}`).innerHTML = `${event.begin_date} - ${event.end_date}`
                    //console.log(,new Date(event.begin_date))
                    document.querySelector(`#status_dot_${event.event_id}`).className = new Date() > new Date(event.end_date) ? "red-dot" : new Date() < new Date(event.begin_date) ? "green-dot" : "yellow-dot";
                    //console.log(`event: ${event}`)
                    let moreInfo = (e) => {
                        Swal.fire({
                            title: prevent_html_injection(event.title),
                            text: prevent_html_injection(event.description)
                            //imageUrl: 'images/Carrera tec.jpg',
                            /*imageWidth: 300,
                            imageHeight: 150,
                            imageAlt: 'Imagen de evento',*/
                        })
                    }
                    let more_info_anchor = document.querySelector(`#more_info_${event.event_id}`)
                    if (more_info_last_event_listener)
                        more_info_anchor.removeEventListener("click", more_info_last_event_listener)
                    more_info_anchor.addEventListener("click", moreInfo)
                    more_info_last_event_listener = moreInfo
                }
            }
            update_data();
            //element.addEventListener("click", save_update)
            setInterval(update_data, 30000)
        })

    }
    //console.log(document.getElementById("add_button"));
    let add_button = document.getElementById("add_button")
    if(add_button)
        add_button.addEventListener("click", save_update);
    let delete_buttons = document.querySelectorAll(".fa-trash-alt")
    if(delete_buttons) {
        delete_buttons.forEach(element => {
            element.addEventListener("click", (event) => {
                Swal.fire({
                    icon: 'error',
                    title: '¿Estás seguro de borrar el evento?',
                    //showDenyButton: true,
                    showCancelButton: true,
                    confirmButtonText: `Borrar`,
                }).then((result) => {
                    /* Read more about isConfirmed, isDenied below */
                    if (result.isConfirmed) {
                        let formData = new FormData();
                        let event_id = event.target.getAttribute('data-id');
                        formData.append('event_id', event_id);
                        fetch('event', {
                            method: 'DELETE'
                            , body: formData
                        }).then(response => response.json())
                            .then(response => Swal.fire(response.message, '', 'success').then(isconfirmed => {
                                location.reload()
                            }))
                            .catch(error => console.log(error))
                    } else if (result.isDenied) {
                    }
                })
            })
        });
    }
    /*document.getElementById("logout_button", ()=>{
        console.log('logout')
        fetch('login',{
            method:'DELETE'
        })
    })*/
});

