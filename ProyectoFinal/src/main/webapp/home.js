async function eventRegister() {
      try {
          let result = await Swal.fire({
              title: 'Evento',
              html:
                  '<form id="event_form">' +
                  '<label for="eventName">Nombre del evento</label>' +
                  '<input id="eventName" name="eventName" type="text"/>' +
                  '<label for "eventDescription">Descripción del evento </label>' +
                  '<input id="eventDescription" name="eventDesc" type="textArea"/>' +
                  '<label for="eventCategory">Categoría</label>' +
                  '<select name="eventCat" id="eventCategory">' +
                  '<option value="s">Social</option>' +
                  '<option value="c">Cultural</option>' +
                  '<option value="d">Deportivo</option>' +
                  '<option value="a">Artístico</option>' +
                  '<option value="r">Recreativo</option>' +
                  '</select>' +
                  '<div><label for="eventStartDay">Fecha de inicio evento</label>' +
                  '<input type="date" id="eventStartDay" name="startDay" value="2018-07-22" min="2021-06-12" max="2022-06-12"></div>' +
                  '<div><label for="eventStartHour">Hora inicio del evento</label>' +
                  '<input id="eventStartHour" name="startHour" type="time"></div>' +
                  '<div><label for="eventEndDay">Fecha de cierre del evento</label>' +
                  '<input type="date" id="eventEndDay" name="endDay" value="2018-07-22" min="2021-06-12" max="2022-06-12"></div>' +
                  '<div><label for="eventEndHour">Hora de cierre del evento</label>' +
                  '<input id="eventEndHour" type="time" name="endHour"></div>' +
                  '<label for="eventPrice">Precio del evento</label>' +
                  '<input id="eventPrice" type="number" name="eventPrice">' +
                  '<div><label for="eventImage">Imagen de evento</label>' +
                  '<input id="eventImage" name="image" type="file"></div>'
                  + '</form>',

              showCancelButton: true
          });
          let formData = new FormData(document.getElementById("event_form"));
          fetch('')
          //console.log(formData)
          //console.log(formData.get('eventPrice'))

      }catch(error){
          console.log(error)
      }


}