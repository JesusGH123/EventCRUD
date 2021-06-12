async function addUser() {
    try {
        let result = await Swal.fire({
            title: 'Nuevo usuario',
            html:
                '<form id="new_user_form">' +
                '<label for="username">Nombre de usuario</label>' +
                '<input id="username" name="username" type="text">' +
                '<label for="isAdmin">Usuario administrador</label>' +
                '<input id="isAdmin" name="isAdmin" type="text">' +
                '<div><label for="password">Contraseña</label>' +
                '<input name="password" id="password" type="password"></div>' +
                '</form>',
            showCancelButton: true
        });
        let formData = new FormData();
        formData.append("username", document.getElementById("username").value);
        formData.append("isAdmin", document.getElementById("isAdmin").checked);
        formData.append("password", document.getElementById("password").value);
        await fetch('user',{
            method:'POST'
            ,body:formData
        });

    }catch(error){
        console.log(error)
    }
}

function deleteUser() {
    let deleteId = event.target.getAttribute("data-id")
    let deleteBtns = document.getElementsByClassName("fa-trash-alt");

    let formData = new FormData();
    formData.append("deleteId", deleteId);

    deleteBtns.forEach(function(element){
        alert("CLICKKKKKKK!!!!");
        element.addEventListener("click", function(event){
            fetch('user',{
                method:'DELETE',
                body: { id: deleteId }
            }).then(
                response => {
                    response.json();
                    location.reload();
                    console.log(response);
                }
            );
        })
    })
}