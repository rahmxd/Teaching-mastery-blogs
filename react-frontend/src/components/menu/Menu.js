import React from 'react'
import { Link } from 'react-router-dom'
import { Col, Row, ListGroup } from 'reactstrap'

const Menu = () => {
  return (
    <ListGroup>
        <Row>
            <Col>
                <Link className="list-group-item" to="/" style={{backgroundColor: "gray", color:"white"}}>
                    Home
                </Link>
            </Col>
            <Col>
                <Link className="list-group-item" to="/create" style={{backgroundColor: "gray", color:"white"}}>
                    Add Blog
                </Link>
            </Col>
        </Row>
    </ListGroup>
  )
}

export default Menu