import React from 'react';
import { Form, Row, Col, Button,Image, Table, ButtonGroup} from 'react-bootstrap';
import VideoAxios from '../apis/VideoAxios';

class User extends React.Component {

    constructor(props) {
        super(props);
        let lista=[]

      
        let korisnik = {
          id:"",
          korisnickoIme: "",
          datumKreiranja: "",
            opis: "",
          brojPratilaca: "",
          blokiran:"",
          uloga:"",

          
          
      }
      let flag = false
      this.state = { 
        videi:[],
        korisnik:korisnik,
        idpratioca:[],
        pratioci:[],
        flag:true,
        flag2:true,
        // stanja:[],
        // sprintovi:[]
        // search: search
      }
    }
    

    componentDidMount(){
        // this.getZgrade();
        // this.getZgrade1();
       // var colors = this.state.idpratioca.toLocaleString()
       // console.log(colors.indexOf(2,0)) 
        //this.getPorukaById(this.props.match.params.id)
        VideoAxios.get('/korisnici/' + this.props.match.params.id)
        .then(res => {
            // handle success
            console.log(res.data);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({korisnik:res.data,videi:res.data.videi,idpratioca:res.data.idpratioca,
            pratioci:res.data.pratioci});
            console.log(window.localStorage['id']);
            let ulogovani = window.localStorage['id'];
            let a= this.state.idpratioca.toString()
           // let b= this.state.video.id
           // console.log(b);
           // console.log(this.state.video.blokiran+"sasas")
            this.setState({flag:(a.includes(ulogovani)),flag2:(this.state.korisnik.id!=window.localStorage['id'])})
       })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
        console.log("test2");
        
        this.state.idpratioca.forEach(element => {
              console.log(element.key)
             
        });
 
        

        
        }

    
  


async subscribe(e){
  e.preventDefault();
      
     
        try{

       
            let config = { 
                params: {
                  iDpratioca: window.localStorage['id']
                }
              };
    let response = await VideoAxios.get("/korisnici/subscribe/" +this.state.korisnik.id, config);
            //console.log(zadatakDTO)
   window.location.reload()
}catch(error){
    alert("Couldn't save the linija");
}
}

async unsubscribe(e){
  e.preventDefault();


  try{

 
      let config = { 
          params: {
            iDpratioca: window.localStorage['id']
          }
        };
let response = await VideoAxios.get("/korisnici/unsubscribe/" +this.state.korisnik.id, config);
      //console.log(zadatakDTO)
window.location.reload()
}catch(error){
alert("Couldn't save the linija");
}
}
// goToEdit(id) {
//   this.props.history.push("/videoEdit/"+id);

// }
delete(videoId) {
  VideoAxios.delete('/videos/' + videoId)
  .then(res => {
      // handle success
      console.log(res);
      alert('Video was deleted successfully!');
     // this.deleteFromState(takmicenjeId); // ili refresh page-a window.location.reload();
     window.location.reload();
  })
  .catch(error => {
      // handle error
      console.log(error);
      alert('Error occured please try again!');
   });
} 
add(id) {
    this.props.history.push("/videoAdd/"+id);
  }
sort1(){
  this.state.videi.sort((a,b) => a.brojPregleda-b.brojPregleda);
  this.setState({

    videi: this.state.videi
    });
  }
  sort2(){
    this.state.videi.sort((a,b) => b.brojPregleda-a.brojPregleda);
    this.setState({

        videi: this.state.videi
        });    
}
sort3(){
    this.state.videi.sort((a,b) => Date.parse(b.datumKreiranja)-Date.parse(a.datumKreiranja));
    this.setState({

        videi: this.state.videi
        });    
}
sort4(){
    this.state.videi.sort((a,b) => Date.parse(a.datumKreiranja)-Date.parse(b.datumKreiranja));
    this.setState({

        videi: this.state.videi
        });    
    }
        goToVideo(id) {
            this.props.history.push("/video/"+id);
          }
    render(){
          if((window.localStorage['role']=="ROLE_KORISNIK" && window.localStorage['id']!=this.state.korisnik.id)
          ||window.localStorage['role']==null &&(this.state.video.vidljivost=='PRIVATNI'||this.state.video.blokiran==true)
        ){

             return (
                  <div><h1>You are not authorized for this video</h1>
                  <Button onClick={(event)=>{this.props.history.push("/videos");}}>Go to main page</Button></div>
             );
         
           // alert("Niste autorizovani za pristup zeljenom videu")
          }
          else {
        return (
            <>
            
             <h1>User</h1>
        
             <Form style={{marginTop:35}}>
            <Row>
            <Col  xs="12" sm="10" md="8">
           
            <Table>
           
            <div class="card-body" style={{backgroundColor:'transparent',paddingLeft:'150px'}}>
            <Table >
            <tr>Korisnicko ime</tr>
            <td>{this.state.korisnik.korisnickoIme}</td>
            
            <tr>Broj pratilaca</tr>
            <td>{this.state.korisnik.brojPratilaca}</td>
            <tr>Datum kreiranja</tr>
            <td>{this.state.korisnik.datumKreiranja}</td>
   
            <tr>Uloga</tr>
            <td>{this.state.korisnik.uloga}</td>

            <tr>Blokiran</tr>
            <td>{this.state.korisnik.blokiran==true ? "DA":"NE"}</td>
            
            {window.localStorage['role']=='ROLE_ADMIN' ||window.localStorage['id']==this.state.korisnik.id ?
                  [
                  <td><Button variant="danger" onClick={() => this.add(this.state.korisnik.id)}>Add video</Button>
                </td>]
                  :null}
          
          </Table>
         
          </div>
         
         
           
              
            {
            
              //var flag=0,
           // var test= this.state.idpratioca, 
            // new Map();

                              
            this.state.flag== true ?
            [
            <td><Button style={{visibility:this.state.flag2==false ? 'hidden':'visible'}}   onClick={(event)=>{this.unsubscribe(event);}} >UNSUBSCRIBE</Button></td>]
            :<Button style={{visibility:this.state.flag2==false ? 'hidden':'visible'}} onClick={(event)=>{this.subscribe(event);}}>SUBSCRIBE</Button> }
        
                     
                        
    
    
    
         
           
            

            </Table>
            <Table id="movies-table" style={{marginTop:5}}>
           
          
            <tbody>
                
            {this.state.videi.map((video) => {
                return (
                  <tr key={video.id}>
                         {((window.localStorage['role']=="ROLE_KORISNIK" && window.localStorage['id']!=video.korisnikId)
                         ||window.localStorage['role']==null) && (video.vidljivost=="PRIVATNI"||video.vidljivost=="UNLISTED")?
                
                   null:  <div class="card mb-4 box-shadow" style={{marginTop:'auto',marginLeft:'auto',marginRight:'auto'}}>
                   <thead><a href={video.video}><Image  src={video.slicica} style={{height:'200px',width:'200px'}}></Image></a></thead>
                     <div class="card-body" style={{backgroundColor:'floralwhite'}}>
                   <tbody>
                    <tr onClick={() => this.goToVideo(video.id)}>{"Naziv: "+ video.opis+" "}</tr>
                   <tr>{"Otpremio " +video.imeVlasnika+" "+video.prezimeVlasnika}</tr>
               
                   <tr>{"Kreirano "+ video.datumKreiranja}</tr>
                   <tr>{video.brojPregleda+ " pregled/a"}</tr>
                 
                   {window.localStorage['role']=='ROLE_ADMIN' ||window.localStorage['id']==this.state.korisnik.id ?
                  [
                  <td><Button variant="danger" onClick={() => this.delete(video.id)}>Delete</Button>
                </td>]
                  :null}
                    
                  </tbody>
                  
                  </div></div>}
                  </tr> 
                );
              })}
              </tbody>
              <ButtonGroup>
          <Button onClick={()=>this.sort1()}>sort by views ASC</Button><br/>
              <Button onClick={()=>this.sort2()}>sort by views DESC</Button>
             <Button onClick={()=>this.sort3()}>sort by DATE DESC</Button><br/>
             <Button onClick={()=>this.sort4()}>sort by DATE ASC</Button></ButtonGroup>
            
              <Table>
                 <thead>
                     Korisnik prati
              <tr>
                <th>Korisnicko ime</th>
                <th>broj pratilaca</th>
           
                
              </tr>
            </thead>
            <tbody>
           
            {this.state.pratioci.map((korisnik) => {
                return (
                  <tr key={korisnik.id}>
                    <td>{korisnik.korisnickoIme}</td>
                    <td>{korisnik.brojPratilaca}</td>

                
                
                  </tr>
                );
              })}
            </tbody>
            </Table>
          </Table>
         
          
          
          
         
          
        
         
           
     
         
          </Col>
          </Row>
          
         
         


        </Form>
              
            </>
        )
    }}
}

export default User;