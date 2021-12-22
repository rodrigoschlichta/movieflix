import React from 'react';
import { ReactComponent as MovieImage } from '../../core/images/image 2.svg';
import './style.scss';

const Movie = () => (
   
    <div className="movie-genre-content">
        <select className="select-card">
        <option value="valor1">AÇÃO</option>
        <option value="valor2">ANIMAÇÃO</option>
        <option value="valor3">AVENTURA</option>
        </select> 

            <div className = "movie-card">
                <MovieImage />
                <div>
                    <h6 className="movie-title">O Retorno do Rei</h6>
                    <div className="movie-year-container">
                        <h6>2013</h6>
                        <div className="movie-subTitle-container">
                            <h6>O olho do inimigo está se movendo.</h6>
                        </div>
                    </div>
                </div>
        </div>       
    </div>


);

export default Movie;