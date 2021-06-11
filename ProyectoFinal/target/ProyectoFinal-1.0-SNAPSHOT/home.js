async function eventRegister() {
      try {
          let result = await Swal.fire({
              title: 'Evento',
              html:
                  '<form id="event_form">' +
                  '<label for="eventName">Nombre del evento</label>' +
                  '<input id="eventName" name="eventName" type="text"/>' +
                  '<label for "eventDescription">Descripción del evento </label>' +
                  '<input id="eventDescription" name="eventDescription" type="textArea"/>' +
                  '<label for="eventCategory">Categoría</label>' +
                  '<select name="eventCategory" id="eventCategory">' +
                  '<option value="s">Social</option>' +
                  '<option value="c">Cultural</option>' +
                  '<option value="d">Deportivo</option>' +
                  '<option value="a">Artístico</option>' +
                  '<option value="r">Recreativo</option>' +
                  '</select>' +
                  '<div><label for="eventStartDay">Fecha de inicio evento</label>' +
                  '<input type="date" id="eventStartDay" name="eventStartDay" value="2018-07-22" min="2021-06-12" max="2022-06-12"></div>' +
                  '<div><label for="eventStartHour">Hora inicio del evento</label>' +
                  '<input id="eventStartHour" name="eventStartHour" type="time"></div>' +
                  '<div><label for="eventEndDay">Fecha de cierre del evento</label>' +
                  '<input type="date" id="eventEndDay" name="eventEndDay" value="2018-07-22" min="2021-06-12" max="2022-06-12"></div>' +
                  '<div><label for="eventEndHour">Hora de cierre del evento</label>' +
                  '<input id="eventEndHour" type="time" name="eventEndHour"></div>' +
                  '<label for="eventPrice">Precio del evento</label>' +
                  '<input id="eventPrice" type="number" name="eventPrice">' +
                  '<div><label for="eventImage">Imagen de evento</label>' +
                  '<input id="eventImage" name="eventImage" type="file"></div>'
                  + '</form>',

              showCancelButton: true
          });
          let formData = new FormData(document.getElementById("event_form"));
          let post_result = await fetch('event',{
              method:'POST'
              ,body:formData
          });
          console.log(await post_result.json());
          //console.log(formData)
          //console.log(formData.get('eventPrice'))

      }catch(error){
          console.log(error)
      }
}

function moreInfo() {
    Swal.fire({
        title: 'Carrera Tec',
        text: 'Carrera organizada por el Tecnológico de Monterrey, opcion de 5 o 10km',
        imageUrl: 'images/Carrera tec.jpg',
        imageWidth: 300,
        imageHeight: 150,
        imageAlt: 'Imagen de evento',
    })
}

function deleteEvent(){
    Swal.fire({
        icon: 'error',
        title: '¿Estás seguro de borrar el evento?',
        //showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: `Borrar`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            Swal.fire('Deleted!', '', 'success')
        } else if (result.isDenied) {
        }
    })
}