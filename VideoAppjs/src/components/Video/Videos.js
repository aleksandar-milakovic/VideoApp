import React from "react";
import VideoAxios from '../../apis/VideoAxios';
import {Button, ButtonGroup, Table, Form, Col, Row, Image} from 'react-bootstrap';

class Videos extends React.Component {
  constructor(props) {
    super(props);
  

    let search = {
      ime: "",
      sprintId: "",
      
    }
    this.state = { 
      videos: [],
     
      pageNo: 0,
      totalPages: 0,
     
       search: search,
      
    }
  }

  componentDidMount() {
    this.getVideos(0);
  
  }
  deleteFromState(movieId) {
    var movies = this.state.zadaci;
    movies.forEach((element, index) => {
        if (element.id === movieId) {
            movies.splice(index, 1);
            this.setState({zadaci: movies});
        }
    });
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

  delete(takmicenjeId) {
    VideoAxios.delete('/zadaci/' + takmicenjeId)
    .then(res => {
        // handle success
        console.log(res);
        alert('Movie was deleted successfully!');
        this.deleteFromState(takmicenjeId); // ili refresh page-a window.location.reload();
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

   // za pretragu
    // if(this.state.search.ime != ""){
    //   config.params.ime=this.state.search.ime
    //     }
    // if(this.state.search.sprintId != ""){
    //   config.params.sprintId=this.state.search.sprintId
    // }
    // if(this.state.search.prevoznikId != -1){
    //   config.params.prevoznikId=this.state.search.prevoznikId
    // }

    try {

      let result = await VideoAxios.get("/videos", config);
      if (result && result.status === 200){
        this.setState({
          videos: result.data,
          pageNo: pageNo,
          totalPages: result.headers["total-pages"]
          });
          console.log(result.headers)
          console.log(pageNo)

      }
    } catch (error) {
      console.log(error);
    }
  }

//   async getZgrade(){
//     try {
//       let result = await CinemaAxios.get("/sprintovi");
//       if (result && result.status === 200){
//         this.setState({
//           sprintovi: result.data
//           });
//           console.log(result.data)
//       }
//     } catch (error) {
//       console.log(error);
//     }
//   }
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
  goToPrijava(id) {
    this.props.history.push("/prijave/"+id);
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

  render() {
    return (
      <div>
 
      
        <Form style={{marginTop:35}}>
          <Row>
          <Col  xs="12" sm="10" md="8">
           
         
          </Col>
          </Row>
          
         
      

            <Form style={{marginTop:35}}>
            <h3>Pretraga</h3> <br/>
          <Row>
        
          <Col md={6}>
          <Form.Group>
            <Form.Label>Ime zadatka</Form.Label>
            <Form.Control
               onChange={(event) =>this.searchValueInputChange(event)}
               name="ime"
               value={this.state.search.ime}
               as="input"
               type="text">
              
              
            </Form.Control>
          </Form.Group>
          
        

         </Col>
         
         
         
          </Row>

        </Form>
        </Form>

        <br/><br/>
        <div class="row justify-content-center">
         
          <br />

          <br/>
          <br/>

          <Table id="movies-table" style={{marginTop:5,tableLayout:'auto',marginLeft:'300px'}}>
           
           
              {this.state.videos.map((video) => {
                return (
                  <tr key={video.id}>
                    <th><Image  src={video.slicica} style={{height:'200px',width:'200px'}}></Image></th>
                   
                    <tbody>
                     <tr><a href={video.video}>{"Naziv: "+ video.opis+" "}</a></tr>
                    <tr>{"Otpremio " +video.imeVlasnika+" "+video.prezimeVlasnika}</tr>
                    <tr>{"Kreirano "+ video.datumKreiranja}</tr>
                    <tr>{video.brojPregleda+ " pregled/a"}</tr>
                   {/* {window.localStorage['role']=="ROLE_ADMIN"? */}
                {/* //   [
                //   <td><Button variant="danger" onClick={() => this.delete(video.id)}>Delete</Button></td>]
                //   :null}
                //     {window.localStorage['role']=="ROLE_ADMIN"? */}
                {/* //   [
                //   <td><Button style={{backgroundColor:"violet"}} onClick={() => this.goToEdit(video.id)}>Edit</Button></td>
                //    ] :null}
                
                //      {window.localStorage['role']=="ROLE_ADMIN"?
                //   [ */}
                {/* //   <td><Button disabled={video.stanjeIme=='ZAVRSEN'} style={{backgroundColor:"green"}} onClick={(e) => this.createRez(e,video.id)}>Change state</Button></td>]
                //    :null
                   } */}
                   </tbody>
                  </tr>
                );
              })}
          
          </Table>

          <ButtonGroup style={{ marginTop: 25 }}>
          <Button 
            disabled={this.state.pageNo==0} onClick={()=>this.getVideos(this.state.pageNo-1)}>
            Previous
          </Button>
          <Button
            disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getVideos(this.state.pageNo+1)}>
            Next
          </Button>
        </ButtonGroup>

        </div>
      </div>
    );
  }
}

export default Videos;