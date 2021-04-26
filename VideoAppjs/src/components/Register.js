import React from 'react';
import { Form, Row, Col, Button} from 'react-bootstrap';
import VideoAxios from '../apis/VideoAxios';

class Register extends React.Component {

    constructor(props) {
        super(props);
        let korisnik = {
          
          ime: "",
          korisnickoIme: "",
          lozinka: "",
          ponovljenaLozinka: "",
          eMail:"",
          prezime:"",
          datumKreiranja:"",
       
      }
      this.state = { 
        
        korisnik:korisnik,
        // stanja:[],
        // sprintovi:[]
        // search: search
      }
    }
    


      async create(e){
        e.preventDefault();
      
     
        try{
            var vreme = new Date();
            vreme= vreme.getFullYear()+"-"+(vreme.getMonth()<=9 ? "0"+vreme.getMonth():vreme.getMonth())+"-"+(vreme.getDate()<=9 ? "0"+vreme.getDate():vreme.getDate())

          let korisnik = this.state.korisnik;
          let korisnikDTO = {
          
              ime:korisnik.ime ,
              prezime:korisnik.prezime,
              korisnickoIme:korisnik.korisnickoIme,
              eMail:korisnik.eMail,
              datumKreiranja:vreme,
              ponovljenaLozinka:korisnik.ponovljenaLozinka,
              lozinka:korisnik.lozinka
          }

    let response = await VideoAxios.post("/korisnici", korisnikDTO);
            console.log(korisnikDTO)
    this.props.history.push("/");
}catch(error){
    alert("Couldn't save the linija");
}
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
      

    // TODO: Rukovati prihvatom vrednosti na promenu
   
    // TODO: Omogućiti odabir filma za projekciju
    render(){
        return (
            <>
             <h1>Register</h1>
        
             <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
            <Form.Group>
            <Form.Label>Ime</Form.Label>
            <Form.Control
            
              name="ime"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Prezime</Form.Label>
            <Form.Control
            
              name="prezime"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Korisnicko ime</Form.Label>
            <Form.Control
           
              name="korisnickoIme"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>lozinka</Form.Label>
            <Form.Control
           
              name="lozinka"
              as="input"
              type="password"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Ponovljena lozinka</Form.Label>
            <Form.Control
           
              name="ponovljenaLozinka"
              as="input"
              type="password"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Email</Form.Label>
            <Form.Control
         
              name="eMail"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
         
          
         
          </Col>
          </Row>
          
         
          <Button onClick={(event)=>{this.create(event);}}>Create</Button>


        </Form>
              
            </>
        )
    }
}

export default Register;