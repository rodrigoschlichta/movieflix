import React from 'react';
import { ReactComponent as MovieImage } from '../../../../core/images/image 2.svg'
import './style.scss';

const MovieCard = () => (
    <div className = "movie-card">
                <MovieImage />
                <div>
                    <h6 className="movie-title">O Retorno do Rei</h6>
                    <div className="movie-year-container">
                        <h5>2013</h5>
                        <div className="movie-subTitle-container">
                            <h6>O olho do inimigo está se movendo.</h6>
                        </div>
                        <div>
                        </div>
                    </div>
                </div>
        </div> 
);

export default MovieCard;