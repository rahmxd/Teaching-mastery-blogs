
import { Card, CardTitle, Row, Col, Container } from 'reactstrap';
import { ToastContainer } from 'react-toastify';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Menu from './components/menu/Menu';
import ListBlogs from './pages/ListBlogs';
import AddBlog from './pages/AddBlog';
import UpdateBlog from './pages/UpdateBlog';

function App() {
  return (
    <div className="App">
      <Card body inverse style={{backgroundColor:'#333', borderColor:'#333'}}>
        <ToastContainer/>
        <Router>
        <CardTitle>
          <h3>Hello World</h3>
        </CardTitle>
        <Container>
            <Row>
              <Col>
                <Menu/>
              </Col>
            </Row>
            <Row>
              <Col>
                <Routes>
                  <Route exact path="/" component={ListBlogs} />
                  <Route exact path="/create" component={AddBlog} />
                  <Route exact path="/update" component={UpdateBlog} />
                </Routes>
              </Col>
            </Row>
        </Container>
        </Router>
      </Card>
    </div>
  );
}

export default App;
