import React from 'react';
import { Form, Row, Col, Button,Image, Table} from 'react-bootstrap';
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
          
      }
      let flag = false
      this.state = { 
        komentari:[],
        video:video,
        idpratioca:[],
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
        var colors = this.state.idpratioca.toLocaleString()
        console.log(colors.indexOf(2,0)) 
        //this.getPorukaById(this.props.match.params.id)
        VideoAxios.get('/videos/' + this.props.match.params.id)
        .then(res => {
            // handle success
            console.log(res.data.idpratioca);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({video:res.data,komentari:res.data.komentari,idpratioca:res.data.idpratioca});
            console.log(window.localStorage['id']);
            let ulogovani = window.localStorage['id'];
            let a= this.state.idpratioca.toString()
            let b= this.state.video.id
            console.log(b);
            
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
    render(){
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
         
          <thead>{ "Like/Dislike "}<Button disabled='true'>{this.state.video.brojLajkova+"/"+this.state.video.brojDislajkova}</Button></thead> 
             {

              
              console.log(this.state.video)
             }
              
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
                    <td>{komentar.sadrzaj}</td>
                    <td>{komentar.imeVlasnika+ " "+komentar.prezimeVlasnika}</td>
                    <td>{komentar.datumKreiranja}</td>
                    <td>{komentar.brojLajkova+"/"+komentar.brojDislajkova}</td>
                   

                
                  </tr>
                );
              })}
            </tbody>
          </Table>
            </div>
          
          
          
         
          
        
         
           
     
         
          </Col>
          </Row>
          
          {window.localStorage['role']=='ADMIN' ||window.localStorage['id']==this.state.video.korisnikId ?
                  [
                  <td><Button variant="danger" onClick={() => this.delete(this.state.video.id)}>Delete</Button>
                  <Button onClick={(event)=>{this.goToEdit(this.state.video.id);}}>Edit</Button></td>]
                  :null}
         


        </Form>
              
            </>
        )
    }
}

export default Video;