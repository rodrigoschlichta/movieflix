import React from 'react';
import { Link } from 'react-router-dom';
import MovieCard from './components/MovieCard';
import './style.scss';

const Movie = () => (
   
    <div className="movie-genre-content">
        <select className="select-card">
        <option value="valor1">AÇÃO</option>
        <option value="valor2">ANIMAÇÃO</option>
        <option value="valor3">AVENTURA</option>
        </select>  

        <div className = "card-movies">
        <Link to= "/movies/1"><MovieCard /></Link> 
        <Link to= "/movies/2"><MovieCard /></Link>   
        <Link to= "/movies/3"><MovieCard /></Link>   
        <Link to= "/movies/4"><MovieCard /></Link>    
        <Link to= "/movies/5"><MovieCard /></Link>  
        <Link to= "/movies/6"><MovieCard /></Link>    
        </div>
        

    </div>


);

export default Movie;