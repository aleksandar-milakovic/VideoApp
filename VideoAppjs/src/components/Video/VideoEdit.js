import React from 'react';
import { Form, Row, Col, Button,Image, Table} from 'react-bootstrap';
import VideoAxios from '../../apis/VideoAxios';

class VideoEdit extends React.Component {

    constructor(props) {
        super(props);
        let lista=[]

        var asa= false
        let video = {
          id:"",
          slicica: "",
          video: "",
            opis: "",
          vidljivost: "",
          dozvoljeniKomentari:"",
          vidljivostRejtinga:"",
          blokiran:"",
          brojDislajkova:"",
          korisnikId:"",
          video:"",
          slicica:"",
          datumKreiranja:""
       
          
      }
      let flag = false
      this.state = { 
       
        video:video,
       
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
        
        VideoAxios.get('/videos/' + this.props.match.params.id)
        .then(res => {
            // handle success
            console.log(res.data.idpratioca);
           /* if(res.data.idpratioca.length==0){
                res.data.idpratioca.push(0)
            }*/
            this.setState({video:res.data});
            console.log(window.localStorage['id']);
            let ulogovani = window.localStorage['id'];
          //  let a= this.state.idpratioca.toString()
            let b= this.state.video.id
            console.log(b);
            
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
    
              let video = this.state.video;
              let videoDTO = {
                  id:video.id,
                  opis:video.opis ,
                  vidljivost:video.vidljivost,
                  vidljivostRejtinga:video.vidljivostRejtinga,
                  dozvoljeniKomentari:video.dozvoljeniKomentari,
                  video:video.video,
                  slicica:video.slicica,
                  datumKreiranja:video.datumKreiranja,
                  korisnikId:video.korisnikId
                  
              }
    
        let response = await VideoAxios.put("/videos/" +this.state.video.id, videoDTO);
                console.log(videoDTO)
        this.props.history.push("/video/"+this.state.video.id);
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
    let video = this.state.video;
    video[name] = value;
  
    this.setState({ video: video });
  }
    render(){
        return (
            <>
             <h1>Edit Video</h1>
        
             <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
            <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control
             value={this.state.video.opis}
              name="opis"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          
         
          <Form.Group>
            <Form.Label>Vidljivost rejtinga</Form.Label>
            <Form.Control
             value={this.state.video.vidljivostRejtinga}
              name="vidljivostRejtinga"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={true} >
               DA
              </option>
              <option value={false} >
               NE
              </option>
              

              onChange={(e) => this.valueInputChanged(e)}
            </Form.Control>
          </Form.Group>
         
          
         
          
        
         
            <Form.Group>
            <Form.Label>Vidljivost</Form.Label>
            <Form.Control
               onChange={(event) => this.valueInputChanged(event)}
               name="vidljivost"
               value={this.state.video.vidljivost}
               as="select">
               <option value={-1}></option>
              
                 
                   <option value={'JAVNI'} >
                    JAVNI
                   </option>
                   <option value={'UNLISTED'} >
                    UNLISTED
                   </option>
                   <option value={'PRIVATNI'} >
                    PRIVATNI
                   </option>
                 
               
            </Form.Control>
          </Form.Group>
          
          <Form.Group>
            <Form.Label>Dozvoljeni komentari</Form.Label>
            <Form.Control
             value={this.state.video.dozvoljeniKomentari}
              name="dozvoljeniKomentari"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={true} >
               DA
              </option>
              <option value={false} >
               NE
              </option>
              

              onChange={(e) => this.valueInputChanged(e)}
            </Form.Control>
          </Form.Group>
         
          </Col>
          </Row>
          
         
          <Button onClick={(event)=>{this.create(event);}}>Edit</Button>


        </Form>
              
            </>
        )
    }
}

export default VideoEdit;