import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import Aside from './Aside';
import Post from './Post';
import { Container, Row } from 'react-bootstrap';
import Slider from './Slider';

const Containers = styled.div`
   max-width: 100%;
   width: 1320px;
   display:flex;
   margin-left:auto;
   margin-right:auto;
`;
const Posts = styled.div`
   flex: 9;
   display:flex;
   align-items: flex-start;
   justify-content: space-between;
`;

const Home = () => {
   
    const [mylist, setMylist] = useState([]);
    useEffect(()=>{
       axios.get('/myblog/blist',{
         headers: {
            'Content-Type':'application/json'
         }
       })
       .then(res => {
           setMylist(res.data);
           console.log(res.data);
       });      
    }, []);

    const removeHTMLTags = (html) => {
      return html.replace(/<[^>]*>/g, '');
    }

    return (
        <>
        <Slider />
        <Containers>
           <Posts>
             <Container>
                <Row className="my-5">
                  {
                     mylist.map((list, index)=>(
                        <Post 
                           key={list.blog.id}
                           id={list.blog.num} 
                           writer={list.blog.writer}
                           newname={list.file.newname}
                           categorya={list.blog.categorya}
                           categoryb={list.blog.categoryb}
                           title={list.blog.title}
                           content={removeHTMLTags(list.blog.content)}
                           wdate={list.blog.wdate}
                        />   
                     )) 
                  }
                </Row>
             </Container>     
           </Posts> 
           <Aside />  
        </Containers>
        </>
    );
};

export default Home;