export let prevent_html_injection = (content)=>{
    return content.replace(/</g, "&lt;").replace(/>/g, "&gt;")
}