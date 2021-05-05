import React from "react";
import VideoAxios from "../apis/VideoAxios";
import {Button, ButtonGroup, Table, Form, Col, Row} from 'react-bootstrap';

class Users extends React.Component {
  constructor(props) {
    super(props);
   

    let search = {
      ime: "",
    korisnickoIme: "",
    prezime:"",
    eMail:"",
    uloga:""

      
    }
    this.state = { 
      korisnici: [],
      sprintovi: [],
      stanja:[],
      pageNo: 0,
      totalPages: 0,
      
       search: search,
      
       
    }
  }

  componentDidMount() {
    this.getUsers(0);
   
  }
  deleteFromState(korisnikId) {
    var korisnik = this.state.korisnici;
    korisnik.forEach((element, index) => {
        if (element.id === korisnikId) {
            korisnik.splice(index, 1);
            this.setState({korisnik: korisnik});
        }
    });
}




  delete(korisnikId) {
    VideoAxios.delete('/korisnici/' + korisnikId)
    .then(res => {
        // handle success
        console.log(res);
        alert('User was deleted successfully!');
        this.deleteFromState(korisnikId); // ili refresh page-a window.location.reload();
    })
    .catch(error => {
        // handle error
        console.log(error);
        alert('Error occured please try again!');
     });
}

  
  async getUsers(pageNo) {
    let config = { 
      params: {
        pageNo: pageNo
      }
    };

   // za pretragu
    if(this.state.search.ime != ""){
      config.params.ime=this.state.search.ime
        }
    if(this.state.search.prezime != ""){
      config.params.prezime=this.state.search.prezime
    }
    if(this.state.search.korisnickoIme != ""){
      config.params.korisnickoIme=this.state.search.korisnickoIme
    }
    if(this.state.search.uloga != ""){
        config.params.uloga=this.state.search.uloga
      }
      if(this.state.search.eMail != ""){
        config.params.eMail=this.state.search.eMail
      }

    try {

      let result = await VideoAxios.get("/korisnici", config);
      if (result && result.status === 200){
        this.setState({
          korisnici: result.data,
          pageNo: pageNo,
          totalPages: result.headers["total-pages"]
          });
          console.log(result.data)
      }
    } catch (error) {
      console.log(error);
    }
  }

  goToEdit(id) {
    this.props.history.push("/user/"+id);
  }
  
  searchValueInputChange(event) {
    let name = event.target.name;
    let value = event.target.value;
    console.log(value)
  
    let search = this.state.search
    search[name] = value;

    this.setState({ search: search });
    this.getUsers(0);
  }
  sort(){
 this.state.korisnici.sort(function(a, b) {
     if(a.ime < b.ime) return 1;
     if(a.ime > b.ime) return -1;
     return 0;
    })
    this.setState({

        korisnici: this.state.korisnici
        });
  }
  sort1(){
    this.state.korisnici.sort(function(a, b) {
        if(a.ime < b.ime) return -1;
        if(a.ime > b.ime) return 1;
        return 0;
       })
       this.setState({
   
           korisnici:this.state.korisnici
           });
     }
  sort2(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.prezime < b.prezime) return 1;
     if(a.prezime > b.prezime) return -1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort3(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.prezime < b.prezime) return -1;
     if(a.prezime > b.prezime) return 1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort4(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.korisnickoIme < b.korisnickoIme) return -1;
     if(a.korisnickoIme > b.korisnickoIme) return 1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort5(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.korisnickoIme < b.korisnickoIme) return 1;
     if(a.korisnickoIme > b.korisnickoIme) return -1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort6(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.eMail < b.eMail) return 1;
     if(a.eMail > b.eMail) return -1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort7(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.eMail < b.eMail) return -1;
     if(a.eMail > b.eMail) return 1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort8(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.uloga < b.uloga) return -1;
     if(a.uloga > b.uloga) return 1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
  sort9(){
    const myData = this.state.korisnici
    .sort(function(a, b) {
     if(a.uloga < b.uloga) return 1;
     if(a.uloga > b.uloga) return -1;
     return 0;
    })
    this.setState({

        korisnici:this.state.korisnici
        });
  }
 

  render() {
    return (
      <div>
        <h1>Users</h1>
        <div class="btn-group dropright">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Dropright
  </button>
  <div class="dropdown-menu">
  
  </div>
</div>
            <Form style={{marginTop:35}}>
            <h3>Search</h3> <br/>
          <Row>
        
          <Col md={6}>
          <Form.Group>
            <Form.Label>Korisnicko ime</Form.Label>
            <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="korisnickoIme"
               value={this.state.search.korisnickoIme}
               as="input"
               type="text">
              </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label> Ime</Form.Label>
            <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="ime"
               value={this.state.search.ime}
               as="input"
               type="text">
              
              
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Prezime</Form.Label>
            <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="prezime"
               value={this.state.search.prezime}
               as="input"
               type="text">
              
              
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Uloga</Form.Label>
            <Form.Control
               onChange={(event) => this.searchValueInputChange(event)}
               name="uloga"
               value={this.state.search.uloga}
               as="select">
               <option value={-1}></option>
              <option value={'ADMIN'}>ADMIN</option>
                   <option value={'KORISNIK'}>KORISNIK </option>
              
            </Form.Control>
          </Form.Group>
          
        

         </Col>
         
         
         
          </Row>

        </Form>
     
        <br/><br/>
        <div>
         
          <br />

          <br/>
          <br/>

          <Table id="movies-table" style={{marginTop:5}}>
            <thead>
              <tr>
                <th>Korisnicko ime</th>
                <th>Ime</th>
                <th>Prezime</th>
                <th>Email</th>
                <th>Uloga</th>
                
              </tr>
            </thead>
            <tbody>
              {this.state.korisnici.map((korisnik) => {
                return (
                  <tr key={korisnik.id}>
                    <td>{korisnik.korisnickoIme}</td>
                    <td>{korisnik.ime}</td>
                    <td>{korisnik.prezime}</td>
                    <td>{korisnik.eMail}</td>
                    <td>{korisnik.uloga}</td>

                 
                    {window.localStorage['role']=="ROLE_ADMIN"?
                  [
                  <td><Button variant="danger" onClick={() => this.delete(korisnik.id)}>Delete</Button></td>]
                  :null}
                    {window.localStorage['role']=="ROLE_ADMIN"?
                  [
                  <td><Button style={{backgroundColor:"violet"}} onClick={() => this.goToEdit(korisnik.id)}>View profile</Button></td>
                   ] :null}
                
                
                  </tr>
                );
              })}
            </tbody>
          </Table>
         

          <div class="btn-group-vertical">
 
                <ButtonGroup style={{height:'80px',backgroundColor:'yellowgreen'}}>
         <Button onClick={()=>this.sort()}>sort by ime DESC</Button>
             <Button onClick={()=>this.sort1()}>sort by ime ASC</Button><br/>
              <Button onClick={()=>this.sort2()}>sort by prezime ASC</Button>
             <Button onClick={()=>this.sort3()}>sort by prezime DESC</Button><br/>
             <Button onClick={()=>this.sort4()}>sort by korisnickoIme ASC</Button>
             <Button onClick={()=>this.sort5()}>sort by korisnickoIme DESC</Button><br/>
             <Button onClick={()=>this.sort7()}>sort by email  ASC</Button>
             <Button onClick={()=>this.sort6()}>sort by email DESC</Button>
             <Button onClick={()=>this.sort8()}>sort by uloga  ASC</Button>
             <Button onClick={()=>this.sort9()}>sort by uloga DESC</Button></ButtonGroup></div>
          <ButtonGroup style={{ marginTop: 25 }}>
          <Button 
            disabled={this.state.pageNo==0} onClick={()=>this.getUsers(this.state.pageNo-1)}>
            Previous
          </Button>
          <Button
            disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getUsers(this.state.pageNo+1)}>
            Next
          </Button>
        </ButtonGroup>

        </div>
      </div>
    );
  }
}

export default Users;