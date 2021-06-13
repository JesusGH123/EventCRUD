let logout = async (event)=>{
    console.log('logout function')
    try {
        let formData = new FormData();
        formData.append("type", "logout");
        let result = await fetch('login', {
            method: 'POST',body: formData
        })
        console.log(`result: ${await result.json()}`)
        location.reload()
    }catch(error){
        console.log('error:',error)
    }
}
