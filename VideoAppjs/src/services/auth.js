import VideoAxios from "../apis/VideoAxios"
import jwt_decode from "jwt-decode"

export const login = async function(username, password){
    const cred={
        username:username,
        password: password
    }

    try{
        if(username==null || username ==""){
            alert("You must enter username")
        }
        if(password==null || password==""){
            alert("You must enter password")
        }
        const ret = await VideoAxios.post('korisnici/auth', cred);
        const decoded = jwt_decode(ret.data)
        console.log(decoded)
        window.localStorage.setItem('role', decoded.id.authority)
        window.localStorage.setItem('id', decoded.role.authority)
        window.localStorage.setItem('jwt', ret.data);
      
    window.location.replace("/")
    }catch(error){
        console.log(error);
        alert("Username or password dont match, choose register if you dont have account")
    }
}

export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.localStorage.removeItem('role');
    window.localStorage.removeItem('id')
    window.localStorage.clear()
    window.location.reload();
}