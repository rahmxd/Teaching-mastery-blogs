import React from 'react';
import { Button, Card, CardBody, CardText, CardTitle, Container } from 'reactstrap';
import { Link } from 'react-router-dom';

const Blog = ({blog}) => {
  return (
    <Card body inverse color="info">
        <CardBody>
            <CardTitle className="font-weight-bold">
                {blog.title}
            </CardTitle>
            <CardText>
                {blog.content}
            </CardText>
            <Container>
                <Link className="btn btn-primary" to="/update">
                    Edit
                </Link>
                <Button>
                    Delete
                </Button>
            </Container>
        </CardBody>
    </Card>
  )
}

export default Blog