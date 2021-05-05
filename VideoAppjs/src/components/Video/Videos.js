import React from "react";
import VideoAxios from '../../apis/VideoAxios';
import {Button, ButtonGroup, Table, Form, Col, Row, Image} from 'react-bootstrap';

class Videos extends React.Component {
  constructor(props) {
    super(props);


    let search = {
      datumIVremeOdParametar: null,
      datumIVremeDoParametar: null,
      brojPregledaOd:"",
      brojPregledaDo:"",
      opis:"",
      vlasnikId:"",

      
    }
    this.state = { 
      videos: [],
        
      pageNo: 0,
      totalPages: 0,
     visibility:"visible",
       search: search,
       korisnici:[],
      
    }
  }

  componentDidMount() {
    this.getVideos(0);
    this.getKorisnici();
    
  
  }
  
  


valueInputChanged(e) {
  let input = e.target;

  let name = input.name;
  let value = input.value;
console.log(value)
  let poruka = this.state.zadatak;
  poruka[name] = value;

  this.setState({ zadatak: poruka });
}
  delete(videoId) {
        VideoAxios.delete('/videos/' + videoId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Movie was deleted successfully!');
           // this.deleteFromState(takmicenjeId); // ili refresh page-a window.location.reload();
           this.props.history.push("/videos")
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }
 

  //TODO prokomentarisati sto sam promenio da se radi sa pageNo, a ne changeDir!!
  async getVideos(pageNo) {
    let config = { 
      params: {
        pageNo: pageNo
      }
    };

    //za pretragu
     if(this.state.search.opis != null){
       config.params.opis=this.state.search.opis
         }
     if(this.state.search.brojPregledaOd != 0){
       config.params.brojPregledaOd=this.state.search.brojPregledaOd
     }
     if(this.state.search.brojPregledaDo != 0){
       config.params.brojPregledaDo=this.state.search.brojPregledaDo
     }
     if(this.state.search.datumIVremeOdParametar != null){
        config.params.datumIVremeOdParametar=this.state.search.datumIVremeOdParametar
      }
      if(this.state.search.datumIVremeDoParametar != null){
        config.params.datumIVremeDoParametar=this.state.search.datumIVremeDoParametar
      }
      if(this.state.search.vlasnikId != ""){
        config.params.vlasnikId=this.state.search.vlasnikId
      }
     
     


    try {

      let result = await VideoAxios.get("/videos", config);
      //result.data.sort((a,b) => b.brojPregleda-a.brojPregleda);
      if (result && result.status === 200){

        this.setState({

          videos: result.data,
          pageNo: pageNo,
          totalPages: result.headers["total-pages"]
          });
          console.log(result.headers)
          console.log(pageNo)
         

          this.state.videos.sort((a,b) => b.brojPregleda-a.brojPregleda
          ); 
          console.log(result.data);
      }
    } catch (error) {
      console.log(error);
    }
  }

  async getKorisnici(){
    try {
      let result = await VideoAxios.get("/korisnici");
      if (result && result.status === 200){
        this.setState({
          korisnici: result.data
          });
          console.log(result.data)
      }
    } catch (error) {
      console.log(error);
    }
  }
//   async getZgrade1(){
//     try {
//       let result = await CinemaAxios.get("/stanja");
//       if (result && result.status === 200){
//         this.setState({
//           stanja: result.data
//           });
//           console.log(result.data)
//       }
//     } catch (error) {
//       console.log(error);
//     }
//   }
  goToEdit(id) {
    this.props.history.push("/poruke/"+id);
  }
  changeVisibility() {
    this.setState({visibility:"hidden"})
  }
  changeVisibility2() {
    this.setState({visibility:"visible"})
  }

  searchValueInputChange(event) {
    let name = event.target.name;
    let value = event.target.value;
    console.log(value)
    //Ovo ne radi za objekta sa objektima u sebi (shallow copy)
    let search = this.state.search
    search[name] = value;

    this.setState({ search: search });
    this.getVideos(0);
  }

  doSearch() {
    this.getVideos(0);
  }
  sort(){
    const myData = this.state.videos
    .sort(function(a, b) {
     if(a.opis < b.opis) return 1;
     if(a.opis > b.opis) return -1;
     return 0;
    })
    this.setState({

        videos: this.state.videos
        });
  }
  sort1(){
    const myData = this.state.videos
    .sort(function(a, b) {
     if(a.opis < b.opis) return -1;
     if(a.opis > b.opis) return 1;
     return 0;
    })
    this.setState({

        videos: this.state.videos
        });
  }
  sort2(){
    const myData = this.state.videos
    .sort(function(a, b) {
     if(a.imeVlasnika < b.imeVlasnika) return 1;
     if(a.imeVlasnika > b.imeVlasnika) return -1;
     return 0;
    })
    this.setState({

        videos: this.state.videos
        });
  }
  sort3(){
    const myData = this.state.videos
    .sort(function(a, b) {
     if(a.imeVlasnika < b.imeVlasnika) return -1;
     if(a.imeVlasnika > b.imeVlasnika) return 1;
     return 0;
    })
    this.setState({

        videos: this.state.videos
        });
  }
  sort4(){
  this.state.videos.sort((a,b) => a.brojPregleda-b.brojPregleda);
  this.setState({

    videos: this.state.videos
    });
  }
  sort5(){
    this.state.videos.sort((a,b) => b.brojPregleda-a.brojPregleda);
    this.setState({

        videos: this.state.videos
        });    
}
sort6(){
    this.state.videos.sort((a,b) => Date.parse(b.datumKreiranja)-Date.parse(a.datumKreiranja));
    this.setState({

        videos: this.state.videos
        });    
}
sort7(){
    this.state.videos.sort((a,b) => Date.parse(a.datumKreiranja)-Date.parse(b.datumKreiranja));
    this.setState({

        videos: this.state.videos
        });    
    }
        goToVideo(id) {
            this.props.history.push("/video/"+id);
          }

  render() {
    return (
      <div>
 
      
        <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
           
         
          </Col>
          </Row>
          
         
      
  
            <Form style={{marginTop:35}}>
          
         
          <Row>
       
          <Col md={6}>
              <Table>
        
          <div style={{visibility:this.state.visibility}} > 
          <thead>Search</thead> <br/>
            <tbody>
          <Form.Group>
            <td>Opis</td>
            <td><Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="opis"
               value={this.state.search.opis}
               as="input"
               type="text">
              
              
            </Form.Control></td>
          </Form.Group>
          <Form.Group>
            <td>Broj pregleda od</td>
           <td> <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="brojPregledaOd"
               value={this.state.search.brojPregledaOd}
               as="input"
               type="text">
              
              
            </Form.Control></td>
          </Form.Group>
          <Form.Group>
            <td>Broj pregleda do</td>
            <td><Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="brojPregledaDo"
               value={this.state.search.brojPregledaDo}
               as="input"
               type="text">
              
              
            </Form.Control></td>
          </Form.Group>
          <Form.Group>
            <td>Datum kreiranja od</td>
            <td><Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="datumIVremeOdParametar"
               value={this.state.search.datumIVremeOdParametar}
               as="input"
               type="date">
              
              
            </Form.Control></td>
          </Form.Group>
          <Form.Group>
            <td>Datum kreiranja do</td>
           <td> <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="datumIVremeDoParametar"
               value={this.state.search.datumIVremeDoParametar}
               as="input"
               type="date">
              
              
            </Form.Control></td>
          </Form.Group>

          <Form.Group>
            <td>Vlasnik</td>
            <td><Form.Control
               onChange={(event) => this.searchValueInputChange(event)}
               name="vlasnikId"
               value={this.state.search.vlasnikId}
               as="select">
               <option value={""}></option>
               {this.state.korisnici.map((korisnik) => {
                 return (
                   <option value={korisnik.id} key={korisnik.id}>
                     {korisnik.ime+" "+korisnik.prezime}
                   </option>
                 );
               })}
            </Form.Control></td>
          </Form.Group>
        
          </tbody>
       
        

         
         </div>
         <tr> <Button style={{visibility:this.state.visibility=='hidden'? 'hidden':'visible'}} onClick={()=>this.changeVisibility()}>Hide search</Button>
         <Button style={{visibility:this.state.visibility=='visible'? 'hidden':'visible'}}  onClick={()=>this.changeVisibility2()}>Show search</Button></tr>
         </Table>
         </Col>
         <div style={{paddingTop:'75px'}}>
       
         </div>
          </Row>

        </Form>
        </Form>

        <br/><br/>
        <div class="row justify-content-center">
         
          <br/>


         
           
           
              {this.state.videos.map((video) => {
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
                 
            
        
                  </tbody>
                  </div></div>}
                  </tr> 
                );
              })}
          
    
             <ButtonGroup><Button onClick={()=>this.sort()}>sort by naziv DESC</Button>
             <Button onClick={()=>this.sort1()}>sort by naziv ASC</Button><br/>
              <Button onClick={()=>this.sort2()}>sort by username DESC</Button>
             <Button onClick={()=>this.sort3()}>sort by username ASC</Button><br/>
             <Button onClick={()=>this.sort4()}>sort by broj Pregleda ASC</Button>
             <Button onClick={()=>this.sort5()}>sort by broj Pregleda DESC</Button><br/>
             <Button onClick={()=>this.sort7()}>sort by datum otpremanja ASC</Button>
             <Button onClick={()=>this.sort6()}>sort by datum otpremanja DESC</Button></ButtonGroup>
             
         <div style={{marginRight:'auto',marginLeft:'auto',marginTop:'auto'}}> <ButtonGroup style={{ marginTop: 25 }}>
          <Button 
            disabled={this.state.pageNo==0} onClick={()=>this.getVideos(this.state.pageNo-1)}>
            Previous
          </Button>
          <Button
            disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getVideos(this.state.pageNo+1)}>
            Next
          </Button>
        </ButtonGroup></div>

        </div>
      </div>
    );
  }
}

export default Videos;