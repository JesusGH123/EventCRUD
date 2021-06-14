import {prevent_html_injection} from './commonjs/code_injection_avoidance.js';

async function addUserFromRegister() {
        let formData = new FormData(document.getElementById("loginForm"));
        fetch('user',{
            method:'POST'
            ,body:formData
        }).then(
            Swal.fire(
                'Usuario registrado!'
            )
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
                        'Usuario Eliminado Exitosamente!'
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
                            location.reload();
                        }
                    );
                }
            })
        })
    })

    updateBtns.forEach(function(element){
        element.addEventListener("click", function(event){
            let updateId = event.target.getAttribute("data-id");
            let formData = new FormData();
            formData.append('updateId',updateId)
            formData.append('type','single');
            fetch('user',{
                method:'POST',body:formData
            }).then(response=>response.json())
                .then((user)=>{
                    Swal.fire({
                        title: 'Editar usuario',
                        html:
                            '<form id="edit_user_form">' +
                            `<label for="username">Nombre de usuario: ${prevent_html_injection(user.username)}</label>` +
                            //`<input id="username" name="username" value=${prevent_html_injection(user.username)} type="text">` +
                            '<div><label for="isAdmin">Usuario administrador</label>' +
                            `<input id="isAdmin" name="isAdmin" type="checkbox" ${user.isAdmin ? "checked":""} ></div>` +
                            '<label for="isAdmin">¿Cambiar Contraseña?</label>' +
                            `<input id="changePassword" name="changePassword" type="checkbox" >`+
                            '<div><label for="password">Nueva Contraseña: </label>' +
                            '<input name="password" id="password" type="password"></div>' +
                            '</form>',
                        confirmButtonText: 'Actualizar',
                        showCancelButton: true
                    }).then((result)=>{
                            if(result.isConfirmed){
                                //let username = document.getElementById("username");
                                let isAdmin = document.getElementById("isAdmin");
                                let changePassword = document.getElementById("changePassword");
                                let password = document.getElementById("password");
                                let formData = new FormData();
                                if(changePassword.checked && password.value.replace(' ','')===''){
                                    Swal.fire('La contraseña esta vacia', '', 'error')
                                        .then(result => {

                                        })
                                }else {
                                    if (updateId) {
                                        //formData.append("type", "single");
                                        formData.append("user_id", updateId);
                                        //formData.append("username", username.value);
                                        formData.append("isAdmin", isAdmin.checked);
                                        formData.append("changePassword", changePassword.checked);
                                        formData.append("password", password.value);

                                        fetch('user', {
                                            method: 'PUT',
                                            body: formData
                                        }).then(
                                            response => response.json()
                                        ).then(
                                            response => {
                                                Swal.fire(response.message, '', 'success')
                                                    .then(result => {
                                                        location.reload()
                                                    })
                                                //document.getElementById("password").value = response.password;
                                                //document.getElementById("username").value = response.username;
                                                //document.getElementById("isAdmin").checked = response.isAdmin;
                                            }
                                        ).catch(
                                            error => console.log('error:', error)
                                        )
                                    }
                                }
                            }
                        })
                        .catch(error=>{
                            console.log('error:',error)
                        })
                })
                .catch(error=>console.log('error:',error))

        })
    })

    document.getElementById("addBtn").addEventListener("click",async function addUser() {
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
            if(result.isConfirmed) {
                let formData = new FormData();
                let username = document.getElementById("username").value
                let password = document.getElementById("password").value
                if(username.replace(' ','')==='' || password.replace(' ','')==='') {
                    Swal.fire('El usuario o contraseña estan vacios', '', 'error')
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
        }catch(error){
            console.log(error)
        }
    })
})
/*< div
className = "d-flex justify-content-center links" >
    < c
:
choose >
< c
:
when
test = "${param.auth_type==1}" >
                            ¿No
tienes
cuenta ?
    <a href="?auth_type=2">Registrarme</a>
</c:when>
<c:when test="${param.auth_type==2}">
¿Ya tienes cuenta?
<a href="?auth_type=1">Iniciar sesión</a>
</c:when>
</c:choose>
</div>*/


/*
 <!-- <input type="submit" value="${param.auth_type==1?"Iniciar sesión":"Registrar"}" class="btn float-right login_btn"> -->
<c:choose>
                            <c:when test="${param.auth_type==1}">
                                <input type="submit" value="Iniciar sesión" class="btn float-right login_btn">
                            </c:when>
                            <c:when test="${param.auth_type==2}">
                                <input type="submit" value="Registrar" class="btn float-right login_btn" onclick="addUserFromRegister()">
                            </c:when>
                        </c:choose>
* */

/*
*  <c:choose>
                        <c:when test="${param.auth_type==1}">
                            Iniciar sesión
                        </c:when>
                        <c:when test="${param.auth_type==2}">
                            Registrar
                        </c:when>
                    </c:choose>*/