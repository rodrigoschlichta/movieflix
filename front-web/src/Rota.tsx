import React from 'react'
import Navbar from './core/components/Navbar';
import {BrowserRouter,Routes, Route} from 'react-router-dom';
import Genre from './pages/Genre';
import Home from './pages/Home';
import Movie from './pages/Movie';

const Rota = () => (
    <BrowserRouter>
        <Navbar />
        <Routes>
            <Route path='/' element = {<Home />} />
            <Route path='/genres' element = {<Genre />} /> 
            <Route path='/movies' element = {<Movie />} />        
        </Routes>
    </BrowserRouter>
);

export default Rota;