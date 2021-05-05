import React from 'react';
import { Form, Row, Col, Button,Image, Table} from 'react-bootstrap';
import VideoAxios from '../../apis/VideoAxios';
import Video from './Video';

class VideoAdd extends React.Component {

    constructor(props) {
        super(props);
       
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
          datumKreiranja:"",
          brojPregleda:"",
       
          
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
        
       
        
        console.log("test2");
        
      
        

        
        }

        async create(e){
            e.preventDefault();
            
            var a= new Date(),
            a= a.getFullYear()+"-"+(a.getMonth()<=9 ? ("0"+a.getMonth()):a.getMonth())
            +"-"+(a.getDate()<=9 ? ("0"+a.getDate()):a.getDate())
         
            try{
    
              let video = this.state.video;
              let videoDTO = {
                 
                  opis:video.opis ,
                  vidljivost:video.vidljivost,
                  vidljivostRejtinga:video.vidljivostRejtinga,
                  dozvoljeniKomentari:video.dozvoljeniKomentari,
                  video:video.video,
                  slicica:video.slicica,
                  datumKreiranja:a,
                  korisnikId:this.props.match.params.id,
                    brojPregleda:0,
                 
                  
              }
    
        let response = await VideoAxios.post("/videos/" , videoDTO);
                console.log(videoDTO)
        this.props.history.push("/user/"+this.props.match.params.id);
        alert("Video added")
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
             <h1>Add Video</h1>
        
             <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
            <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control
            
              name="opis"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Video</Form.Label>
            <Form.Control
            
              name="video"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Slicica</Form.Label>
            <Form.Control
            
              name="slicica"
              as="input"
              type="text"
              onChange={(e) => this.valueInputChanged(e)}
            ></Form.Control>
          </Form.Group>
          
          
         
          <Form.Group>
            <Form.Label>Vidljivost rejtinga</Form.Label>
            <Form.Control
              onChange={(e) => this.valueInputChanged(e)}
          
              name="vidljivostRejtinga"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={true} >
               DA
              </option>
              <option value={false} >
               NE
              </option>
              

            
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
              onChange={(e) => this.valueInputChanged(e)}
        
              name="dozvoljeniKomentari"
              as="select">
              <option value={-1}></option>
              
                 
              <option value={true} >
               DA
              </option>
              <option value={false} >
               NE
              </option>
              

          
            </Form.Control>
          </Form.Group>
         
         
          </Col>
          </Row>
          
         
          <Button onClick={(event)=>{this.create(event);}}>Add</Button>


        </Form>
              
            </>
        )
    }
}

export default VideoAdd;