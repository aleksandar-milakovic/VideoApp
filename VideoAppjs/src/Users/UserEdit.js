import React from 'react';
import { Form, Row, Col, Button,Image, Table} from 'react-bootstrap';
import VideoAxios from '../apis/VideoAxios';


class UserEdit extends React.Component {

    constructor(props) {
        super(props);
        let lista=[]

        var asa= false
        let korisnik = {
        id:"",
        korisnickoIme:"",
        ime: "",
        prezime: "",
        opis:"",
        staraLozinka: "",
        lozinka: "",
        ponovljenaLozinka:"",
        uloga:"",
        blokiran:"",
        eMail:""
       
          
      }
      let flag = false
      this.state = { 
       
        korisnik:korisnik,
       
        flag:true,
        flag2:true,
        checked:false
        // stanja:[],
        // sprintovi:[]
        // search: search
      }
    }
    

    componentDidMount(){
        // this.getZgrade();
        // this.getZgrade1();
        
        VideoAxios.get('/korisnici/' + this.props.match.params.id)
        .then(res => {
            // handle success
            console.log(res.data);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({korisnik:res.data});
            console.log(window.localStorage['id']);
            let ulogovani = window.localStorage['id'];
          //  let a= this.state.idpratioca.toString()
           // let b= this.state.video.id
           // console.log(b);
            
           // this.setState({flag:(a.includes(ulogovani)),flag2:(this.state.video.korisnikId!=window.localStorage['id'])})
       })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
        console.log("test2");
        
      
        

        
        }

        async create(e){
            e.preventDefault();
          
         
            try{
    
              let korisnik = this.state.korisnik;
              let korisnikDTO = {
                  id:korisnik.id,
                  opis:korisnik.opis ,
                  ime:korisnik.ime,
                  prezime:korisnik.prezime,
                  uloga:korisnik.uloga,
                  blokiran:korisnik.blokiran,
                    eMail:korisnik.eMail,
                  korisnickoIme:korisnik.korisnickoIme,
              
                  
              }
        let response = await VideoAxios.put("/korisnici/" +korisnik.id,korisnikDTO);
                console.log(korisnikDTO)
        this.props.history.push("/user/"+this.state.korisnik.id);
    }catch(error){
        alert("Couldn't save the video");
    }
    }   
    async promenaLozinke(e){
        e.preventDefault();
      
     
        try{

          let korisnik = this.state.korisnik;
        
          let KorisnikPromenaLozinkeDto ={
              korisnickoIme:korisnik.korisnickoIme,
              staraLozinka:korisnik.staraLozinka,
              lozinka:korisnik.lozinka,
              ponovljenaLozinka:korisnik.ponovljenaLozinka
          }

    let response = await VideoAxios.put("/korisnici/promenaLozinke/" +korisnik.id,KorisnikPromenaLozinkeDto);
            console.log(KorisnikPromenaLozinkeDto)
    this.props.history.push("/user/"+this.state.korisnik.id);
}catch(error){
    alert("Couldn't save the video");
}
}   
  
    
   



goToEdit(id) {
  this.props.history.push("/videoEdit/"+id);
}
valueInputChanged(e) {
    let input = e.target;
  
    let name = input.name;
    let value = input.value;
  console.log(value)
    let korisnik = this.state.korisnik;
    korisnik[name] = value;
  
    this.setState({ korisnik: korisnik });
  }
   myFuncti() {
   
    var checkBox = document.getElementById("myCheck");
    // Get the output text
    var text = document.getElementById("aca");
  
    // If the checkbox is checked, display the output text
    if (checkBox.checked == true){
      text.style.visibility = "visible";
    } else {
      text.style.visibility = "hidden";
    }
  }
    render(){
        return (
            <>
             <h1>Edit User</h1>
        
             <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
            <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control
             value={this.state.korisnik.opis}
              name="opis"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          
         
          <Form.Group>
            <Form.Label>Ime</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
            value={this.state.korisnik.ime}
              name="ime"
              as="input">
            </Form.Control>
          </Form.Group>
         
          
         
          
        
         
            <Form.Group>
            <Form.Label>Prezime</Form.Label>
            <Form.Control
               onChange={(event) => this.valueInputChanged(event)}
               name="prezime"
               value={this.state.korisnik.prezime}
               as="input">
              </Form.Control>
          </Form.Group>
          
          <Form.Group>

           
          </Form.Group>
       
         
          {window.localStorage['role']=='ROLE_ADMIN'?
          <div><Form.Group>
            <Form.Label>Blokiran</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
            value={this.state.korisnik.blokiran}
              name="blokiran"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={true} >
               DA
              </option>
              <option value={false} >
               NE
              </option>
              

          
            </Form.Control>
          </Form.Group> <Form.Label>Uloga</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
           value={this.state.korisnik.uloga}
              name="uloga"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={'KORISNIK'} >
               KORISNIK
              </option>
              <option value={'ADMIN'} >
               ADMIN
              </option>
              

          
            </Form.Control></div>:null}
          Change password: <input type='checkbox' id="myCheck" onClick={(event)=>{this.myFuncti();}} ></input>
          
         
          <div id="aca">
         <Form.Group>
            <Form.Label>Stara lozinka</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
           
              name="staraLozinka"
              as="input"
              type="password">
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Lozinka</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
           
              name="lozinka"
              as="input"
              type="password">
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Ponovljena lozinka</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
           
              name="ponovljenaLozinka"
              as="input"
              type="password">
            </Form.Control>
          </Form.Group></div> 

          
          </Col>
          </Row>
          
         
          <Button onClick={(event)=>{this.create(event);this.promenaLozinke(event)}}>Edit</Button>


        </Form>
              
            </>
        )
    }
}

export default UserEdit;