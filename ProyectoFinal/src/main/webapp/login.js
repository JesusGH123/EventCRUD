document.addEventListener("DOMContentLoaded",()=> {
    console.log('DOMContentLoaded in login')
    document.getElementById("addBtn").addEventListener("click", async function () {
        try {
            let result = await Swal.fire({
                title: 'Nuevo usuario',
                html:
                    '<form id="new_user_form">' +
                    '<label for="username">Nombre de usuario</label>' +
                    '<input id="username" name="username" type="text">' +
                    '<div><label for="password">Contraseña</label>' +
                    '<input id="isAdmin" name="isAdmin" type="checkbox" value="true" hidden>'+
                    '<input name="password" id="password" type="password"></div>' +
                    '</form>',
                confirmButtonText: 'Guardar',
                showCancelButton: true
            });
            if(result.isConfirmed) {
                let formData = new FormData();
                let username = document.getElementById("username").value
                let password = document.getElementById("password").value
                if(username.replace(' ','')==='' || password.replace(' ','')==='') {
                    Swal.fire('El Usuario o contraseña estan vacios', '', 'error')
                        .then(result => {

                        })
                }else {
                    formData.append("username", username);
                    formData.append("isAdmin", document.getElementById("isAdmin").checked);
                    formData.append("password", password);
                    await fetch('user', {
                        method: 'POST'
                        , body: formData
                    }).then(
                        (response) =>
                            response.json()
                    ).then((response) => {
                        console.log(response)
                        if (response.type === "repeated" || response.type === "error") {
                            Swal.fire(response.message, '', 'error')
                                .then(result => {
                                })
                        } else {
                            Swal.fire(response.message, '', 'success')
                                .then(result => {
                                    location.reload()
                                })
                        }
                    }).catch(error => {
                        console.log(error)
                    });
                }
            }

        } catch (error) {
            console.log(error)
        }
    })
})