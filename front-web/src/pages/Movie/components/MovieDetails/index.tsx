import React from 'react';
import { useParams } from 'react-router-dom';
import { ReactComponent as MovieImage } from '../../../../core/images/imageDetails.svg'
import MovieListReview from '../MovieListReview';
import MovieReview from '../MovieReview';
import './style.scss';

type ParamsType = {
    movieId: string;
}

const MovieDetails = () => {
    const { movieId } = useParams<ParamsType>();

    console.log(movieId);

    return (
        <div className="movies-details-container">
            <div className="row">
                <div className="col-6 movies-image">
                    <MovieImage />
                </div>
                <div className="col-6 movie-title-details">
                    <h1>O Retorno do Rei</h1>
                    <div className="movie-year-details">
                        <h3>2013</h3>
                        <div className="movie-subTitle-details">
                            <h5>O olho do inimigo está se movendo.</h5>
                        </div>
                        <div className="movie-review-details">
                        <p className = "review">O confronto final entre as forças do bem e do mal que lutam pelo controle do futuro da
                        Terra Média se aproxima. Sauron planeja um grande ataque a Minas Tirith, capital de Gondor, 
                        o que faz com que Gandalf e Pippin partam para o local na intenção de ajudar a resistência. 
                        Um exército é reunido por Theoden em Rohan, em mais uma tentativa de deter as forças 
                        de Sauron. Enquanto isso, Frodo, Sam e Gollum seguem sua viagem rumo à Montanha da Perdição 
                        para destruir o anel. </p>
                        </div>

                    </div>

                </div>
            </div>
            <div>
                <MovieReview />
                
            </div>
            <div>
            < MovieListReview />
            </div>
        </div>


    );

};


export default MovieDetails;