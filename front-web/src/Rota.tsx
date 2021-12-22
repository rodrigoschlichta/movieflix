import React from 'react'
import Navbar from './core/components/Navbar';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import Movie from './pages/Movie';

const Rota = () => (
    <BrowserRouter>
        <Navbar />
        <Routes>
            <Route path="/" element = {<Home />} />
            <Route path="/movies" element = {<Movie />} /> 
            <Route path="/movies/moviesId" element = {<Movie />} />        
        </Routes>
    </BrowserRouter>
);

export default Rota;