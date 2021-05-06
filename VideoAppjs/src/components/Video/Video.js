import React from 'react';
import { Form, Row, Col, Button,Image, Table,ButtonGroup} from 'react-bootstrap';
import VideoAxios from '../../apis/VideoAxios';

class Video extends React.Component {

    constructor(props) {
        super(props);
        let lista=[]

        var asa= false
        let video = {
          id:"",
          slicica: "",
          video: "",
            opis: "",
          datumKreiranja: "",
          imeVlasnika:"",
          prezimeVlasnika:"",
          brojLajkova:"",
          brojDislajkova:"",
          idpratioca:[],
          korisnikId:"",
          vidljivost:"",
          blokiran:"",
          korisnikBlokiran:"",
          vidljivostRejtinga:"",
          dozvoljeniKomentari:""
      }
      let komentar = {
        id:"",
        vlasnikId: window.localStorage['id'],
        videoId: "",
        sadrzaj: "",
        datumKreiranja: "",
       
    }
      let flag = false
      this.state = { 
        komentari:[],
        video:video,
        idpratioca:[],
        flag:true,
        flag2:true,
        sadrzaj:"",
        komentar:komentar
        // stanja:[],
        // sprintovi:[]
        // search: search
      }
    }
    

    componentDidMount(){
        // this.getZgrade();
        // this.getZgrade1();
        var colors = this.state.idpratioca.toLocaleString()
        console.log(colors.indexOf(2,0)) 
        //this.getPorukaById(this.props.match.params.id)
        VideoAxios.get('/videos/' + this.props.match.params.id)
        .then(res => {
            // handle success
            console.log(res.data);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({video:res.data,komentari:res.data.komentari,idpratioca:res.data.idpratioca});
            console.log(window.localStorage['id']);
            let ulogovani = window.localStorage['id'];
            let a= this.state.idpratioca.toString()
            let b= this.state.video.id
            console.log(b);
            console.log(this.state.video.blokiran+"sasas")
            this.setState({flag:(a.includes(ulogovani)),flag2:(this.state.video.korisnikId!=window.localStorage['id'])})
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
        async createComment(e){
          e.preventDefault();
          
          var a= new Date(),
          a= a.getFullYear()+"-"+(a.getMonth()<=9 ? ("0"+a.getMonth()):a.getMonth())
          +"-"+(a.getDate()<=9 ? ("0"+a.getDate()):a.getDate())
       
          try{
  
            let video = this.state.video;
            let komentarDTO = {
               
                videoId:video.id ,
                vlasnikId:window.localStorage['id'],
                datumKreiranja:a,
                sadrzaj:this.state.sadrzaj,
                
               
                
            }
  
      let response = await VideoAxios.post("/komentari/" , komentarDTO);
              console.log(komentarDTO)
      // this.props.history.push("/user/"+this.props.match.params.id);
      var sadrzaj1= this.state.komentar.sadrzaj
      this.setState({sadrzaj1:this.state.sadrzaj})
      console.log(this.state.komentar.sadrzaj)
      window.location.reload()
      alert("comment added")
  }catch(error){
      alert("Couldn't save the video");
  }
  }   

  async editComment(e,id,koment){
    e.preventDefault();
    
    var a= new Date(),
    a= a.getFullYear()+"-"+(a.getMonth()<=9 ? ("0"+a.getMonth()):a.getMonth())
    +"-"+(a.getDate()<=9 ? ("0"+a.getDate()):a.getDate())
    this.setState({sadrzaj:koment})
    try{

      let video = this.state.video;
      let komentarDTO = {
          id:id,
          videoId:video.id ,
          vlasnikId:window.localStorage['id'],
          datumKreiranja:a,
          sadrzaj:this.state.komentar.sadrzaj,
          
         
          
      }

let response = await VideoAxios.put("/komentari/"+id , komentarDTO);
        console.log(komentarDTO)
// this.props.history.push("/user/"+this.props.match.params.id);
var sadrzaj1= this.state.komentar.sadrzaj
this.setState({sadrzaj1:this.state.sadrzaj})
console.log(this.state.komentar.sadrzaj)
window.location.reload()
alert("comment added")
}catch(error){
alert("Couldn't save the video");
}
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

  valueInputChanged(e) {
    let input = e.target;
  
    let name = input.name;
    let value = input.value;
  console.log(value)
 

    let sadrzaj = this.state.komentar;
    sadrzaj[name] = value;
  
    this.setState({ sadrzaj: sadrzaj });
  


  }

    
    getPorukaById(videoId) {
        VideoAxios.get('/videos/' + videoId)
        .then(res => {
            // handle success
            console.log(res.data.idpratioca);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({video:res.data,komentari:res.data.komentari,idpratioca:res.data.idpratioca});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
         console.log(this.state.idpratioca)
    }


async subscribe(e){
  e.preventDefault();
      
     
        try{

       
            let config = { 
                params: {
                  iDpratioca: window.localStorage['id']
                }
              };
    let response = await VideoAxios.get("/videos/subscribe/" +this.state.video.id, config);
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
let response = await VideoAxios.get("/videos/unsubscribe/" +this.state.video.id, config);
      //console.log(zadatakDTO)
window.location.reload()
}catch(error){
alert("Couldn't save the linija");
}
}
goToEdit(id) {
  this.props.history.push("/videoEdit/"+id);

}
delete(videoId) {
  VideoAxios.delete('/videos/' + videoId)
  .then(res => {
      // handle success
      console.log(res);
      alert('Video was deleted successfully!');
     // this.deleteFromState(takmicenjeId); // ili refresh page-a window.location.reload();
     this.props.history.push("/videos")
  })
  .catch(error => {
      // handle error
      console.log(error);
      alert('Error occured please try again!');
   });
} 
sort1(){
  this.state.komentari.sort((a,b) => (a.brojLajkova-a.brojDislajkova)-(b.brojLajkova-b.brojDislajkova));
  this.setState({

    komentari: this.state.komentari
    });
  }
  sort2(){
    this.state.komentari.sort((a,b) => (b.brojLajkova-b.brojDislajkova)-(a.brojLajkova-a.brojDislajkova));
    this.setState({

        komentari: this.state.komentari
        });    
}
sort3(){
    this.state.komentari.sort((a,b) => Date.parse(b.datumKreiranja)-Date.parse(a.datumKreiranja));
    this.setState({

        komentari: this.state.komentari
        });    
}
sort4(){
    this.state.komentari.sort((a,b) => Date.parse(a.datumKreiranja)-Date.parse(b.datumKreiranja));
    this.setState({

        komentari: this.state.komentari
        });    
    }
        goToVideo(id) {
            this.props.history.push("/video/"+id);
          }
    render(){
          if((this.state.video.vidljivost=='PRIVATNI'||this.state.video.blokiran==true
          ||this.state.video.korisnikBlokiran==true)&&(window.localStorage['role']=="ROLE_KORISNIK" && window.localStorage['id']!=this.state.video.korisnikId||
          window.localStorage['role']==null)
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
            
             <h1>Video </h1>
        
             <Form style={{marginTop:35}}>
            <Row>
            <Col  xs="12" sm="10" md="8">
            <div class="card mb-4 box" style={{alignSelf:'center',backgroundColor:'powderblue'}}>
            <Table>
            <thead><a href={this.state.video.video}><Image width='150px' height='150px'  src={this.state.video.slicica} style={{height:'200px',width:'200px'}}></Image></a></thead>
            <div class="card-body" style={{backgroundColor:'floralwhite',paddingLeft:'150px'}}>
            <Table >
            <tr>Opis</tr>
            <td>{this.state.video.opis}</td>
            
            <tr>Otpremio/la</tr>
            <td>{this.state.video.imeVlasnika+" "+this.state.video.prezimeVlasnika}</td>
            <tr>Datum kreiranja</tr>
            <td>{this.state.video.datumKreiranja}</td>
   
            <tr>Broj pregleda</tr>
            <td>{this.state.video.brojPregleda}</td>
   
          
          </Table>
         
          </div>
          { (this.state.video.vidljivostRejtinga==false && 
          (window.localStorage['role']=="ROLE_KORISNIK" 
          && window.localStorage['id']!=this.state.video.korisnikId)
          ||window.localStorage['role']==null)?
          null:
          <div><Button class="like" disabled='true'>
            
          {this.state.video.brojLajkova+"/"+this.state.video.brojDislajkova}</Button> 
         </div>}

             
              
            {
            
              //var flag=0,
           // var test= this.state.idpratioca, 
            // new Map();

                              
            this.state.flag== true  ?
            [
            <td><Button style={{visibility:(this.state.flag2==false || window.localStorage['role']==null) ? 'hidden':'visible'}}   onClick={(event)=>{this.unsubscribe(event);}} >UNSUBSCRIBE</Button></td>]
            :<Button style={{visibility:(this.state.flag2==false || window.localStorage['role']==null) ? 'hidden':'visible'}} onClick={(event)=>{this.subscribe(event);}}>SUBSCRIBE</Button> }
        
                     
                        
    
    
    
         
           
            
              
            </Table>
            { (this.state.video.dozvoljeniKomentari==false && 
          (window.localStorage['role']=="ROLE_KORISNIK" 
          && window.localStorage['id']!=this.state.video.korisnikId
          ||window.localStorage['role']==null))?
          null:
            <Table id="movies-table" style={{marginTop:5}}>
            <thead>
                <p>KOMENTARI</p>
              <tr>
                <th>sadrzaj</th>
                <th>vlasnik komentara</th>
                <th>datum kreiranja</th>
                <th>LIKE/DISLIKE</th>
              
              </tr>
            </thead>
            <tbody>
              {this.state.komentari.map((komentar) => {
                return (
                  <tr key={komentar.id}>
                   <td> <Form.Group>
           
            {/* <Form.Control */}
              {/* onChange={(e) => this.valueInputChanged(e)}
               
              value={this.state.komentar.sadrzaj}
              name="sadrzaj"
              as="textarea"
            
            >
            </Form.Control>*/ }
          </Form.Group>{komentar.sadrzaj}</td> 
                    <td>{komentar.imeVlasnika+ " "+komentar.prezimeVlasnika}</td>
                    <td>{komentar.datumKreiranja}</td>
                    <td>{komentar.brojLajkova+"/"+komentar.brojDislajkova}</td>
                   

                    {/* <Button onClick={(event)=>this.editComment(event,komentar.id,komentar.sadrzaj)}>Edit COMMENT</Button> */}
                  </tr>
                );
              })}
            </tbody>
            Comment: <input type='checkbox' id="myCheck" onClick={(event)=>{this.myFuncti(event);}} ></input>
          
         
          <div id="aca">
         <Form.Group>
            <Form.Label>ENTER comment</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
           
              name="sadrzaj"
              as="input"
              type="text">
            </Form.Control>
          </Form.Group>
          <Button onClick={(event)=>this.createComment(event)}>Add COMMENT</Button></div>
            
          </Table>}
         <ButtonGroup>
          <Button onClick={()=>this.sort1()}>sort by date DESC</Button><br/>
              <Button onClick={()=>this.sort2()}>sort by date ASC</Button>
             <Button onClick={()=>this.sort3()}>sort by likes ASC</Button><br/>
             <Button onClick={()=>this.sort4()}>sort by likes DESC</Button></ButtonGroup>
         
            </div>
          
          
          
         
          
        
         
           
     
         
          </Col>
          </Row>
          
          {window.localStorage['role']=='ROLE_ADMIN' ||window.localStorage['id']==this.state.video.korisnikId ?
                  [
                  <td><Button variant="danger" onClick={() => this.delete(this.state.video.id)}>Delete</Button>
                  <Button onClick={(event)=>{this.goToEdit(this.state.video.id);}}>Edit</Button></td>]
                  :null}
         


        </Form>
              
            </>
        )
    }}
}

export default Video;