import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import MovieCard from './components/MovieCard';
import './style.scss';

const Movie = () => {

    useEffect(() => {

        fetch('http://localhost:3000/movies');

    }, []);

    return(
   
    <div className="movie-genre-content">
        <select className="select-card">
        <option value="valor1">AÇÃO</option>
        <option value="valor2">ANIMAÇÃO</option>
        <option value="valor3">AVENTURA</option>
        <option value="valor1">COMÉDIA</option>
        <option value="valor2">COMÉDIA ROMÂNTICA</option>
        <option value="valor3">DOCUMENTÁRIO</option>
        <option value="valor2">DRAMA</option>
        <option value="valor3">FICÇÃO CIENTÍFICA</option>
        <option value="valor1">MIMIZENTO</option>
        <option value="valor2">MUSICAL</option>
        <option value="valor3">ROMANCE</option>
        <option value="valor2">SUSPENSE</option>
        <option value="valor3">TERROR</option>
        </select>  

        <div className = "card-movies">
        <Link to= "/movies/1"><MovieCard /></Link> 
        <Link to= "/movies/2"><MovieCard /></Link>   
        <Link to= "/movies/3"><MovieCard /></Link>   
        <Link to= "/movies/4"><MovieCard /></Link>    
        <Link to= "/movies/5"><MovieCard /></Link>  
        <Link to= "/movies/6"><MovieCard /></Link>
        <Link to= "/movies/1"><MovieCard /></Link> 
        <Link to= "/movies/2"><MovieCard /></Link>   
        <Link to= "/movies/3"><MovieCard /></Link>   
        <Link to= "/movies/4"><MovieCard /></Link>    
        <Link to= "/movies/5"><MovieCard /></Link>  
        <Link to= "/movies/6"><MovieCard /></Link>    
        </div>
        

    </div>


);

}

export default Movie;