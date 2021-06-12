async function addUser() {
    try {
        let result = await Swal.fire({
            title: 'Nuevo usuario',
            html:
                '<form id="new_user_form">' +
                '<label for="username">Nombre de usuario</label>' +
                '<input id="username" name="username" type="text">' +
                '<label for="isAdmin">Usuario administrador</label>' +
                '<input id="isAdmin" name="isAdmin" type="checkbox" value="true">' +
                '<div><label for="password">Contraseña</label>' +
                '<input name="password" id="password" type="password"></div>' +
                '</form>',
            confirmButtonText: 'Guardar',
            showCancelButton: true
        });
        let formData = new FormData();
        formData.append("username", document.getElementById("username").value);
        formData.append("isAdmin", document.getElementById("isAdmin").checked);
        formData.append("password", document.getElementById("password").value);
        await fetch('user',{
            method:'POST'
            ,body:formData
        }).then(
            response => response.json()
        );

    }catch(error){
        console.log(error)
    }
}

async function addUserFromRegister() {
        let formData = new FormData(document.getElementById("loginForm"));
        fetch('user',{
            method:'POST'
            ,body:formData
        }).then(
            Swal.fire(
                'Usuario registrado!'
            )
            //response => response.json()
        );
}

document.addEventListener("DOMContentLoaded",() => {
    let deleteBtns = document.querySelectorAll(".fa-trash-alt");
    let updateBtns = document.querySelectorAll(".fa-edit");

    deleteBtns.forEach(function(element){
        element.addEventListener("click", function(event){

            Swal.fire({
                title: '¿Estas seguro de que quieres eliminar el usuario?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        'Archivo Eliminado!'
                    )

                    let deleteId = event.target.getAttribute("data-id")
                    let formData = new FormData();
                    formData.append("deleteId", deleteId);
                    fetch('user', {
                        method: 'DELETE',
                        body: formData
                    }).then(response => response.json()).then(
                        response => {
                            //response.json();
                            console.log(response);
                        }
                    );
                }
            })
        })
    })

    updateBtns.forEach(function(element){
        element.addEventListener("click", function(event){
            let updateId = event.target.getAttribute("data-id");
                    Swal.fire({
                    title: 'Editar usuario',
                    html:
                        '<form id="edit_user_form">' +
                        '<label for="username">Nombre de usuario</label>' +
                        '<input id="username" name="username" type="text">' +
                        '<label for="isAdmin">Usuario administrador</label>' +
                        '<input id="isAdmin" name="isAdmin" type="checkbox" value="true">' +
                        '<div><label for="password">Contraseña</label>' +
                        '<input name="password" id="password" type="password"></div>' +
                        '</form>',
                    confirmButtonText: 'Actualizar',
                    showCancelButton: true
                });
                let formData = new FormData();

                let username = document.getElementById("username");
                let isAdmin = document.getElementById("isAdmin");
                let password = document.getElementById("password");

                if(updateId) {
                    formData.append("type", "single");
                    formData.append("user_id", updateId);
                    formData.append("username", username.value);
                    formData.append("isAdmin", isAdmin.checked);
                    formData.append("password", password.value);

                    fetch('user', {
                        method: 'POST',
                        body: formData
                    }).then(
                        response => response.json()
                    ).then(
                        response => {
                            document.getElementById("username").value = response.username;
                            document.getElementById("password").value = response.password;
                            //document.getElementById("isAdmin").checked = response.isAdmin;
                        }
                    ).catch(
                        error => console.log(error)
                    )
                }
        })
    })
})