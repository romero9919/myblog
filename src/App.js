import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import "./index.css";
import Header from './pages/Header';
import Home from './pages/Home';
import About from './pages/About';
import Community from './pages/Community';
import Contact from './pages/Contact';
import Doodle from './pages/Doodle';

const App = () => {
  return (
    <>
      <BrowserRouter>
         <Header />
         <Routes>
            <Route index element={<Home />} />
            <Route path="about" element={<About />} />
            <Route path="contact" element={<Contact />} />
            <Route path="doodle" element={<Doodle />} />
            <Route path="community" element={<Community />} />
         </Routes>
       </BrowserRouter>
    </>
  );
};

export default App;