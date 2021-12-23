import React from 'react'
import Navbar from './core/components/Navbar';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import Movie from './pages/Movie';
import MovieDetails from './pages/Movie/components/MovieDetails';

const Rotas = () => (
    <BrowserRouter>
        <Navbar />
        <Routes>
            <Route path="/" element = {<Home />} />
            <Route path="/movies" element = {<Movie />} /> 
            <Route path="/movies/:movieId" element = {<MovieDetails />} />        
        </Routes>
    </BrowserRouter>
);

export default Rotas;