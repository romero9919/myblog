import React from 'react';
import { Col, Button, Card } from 'react-bootstrap';

const Post = ({id, writer, newname, categorya, categoryb, title, content, wdate}) => {
    
    return (
            <Col md={4} className="mb-4">
                <Card>
                   <Card.Img variant='top' src={'./data/img/'+ newname} alt={newname} /> 
                   <Card.Body>
                      <Card.Title>{id}. {title}
                          <small>
                              <span>{categorya}</span>
                              <span>{categoryb}</span>
                          </small>
                      </Card.Title>
                      <Card.Text>
                         {content} [{wdate}]
                      </Card.Text> 
                      <div className="btnbox">{writer}
                           <Button variant="dark">바로가기</Button>
                      </div>
                   </Card.Body>
                </Card>    
            </Col>
    );
};

export default Post;