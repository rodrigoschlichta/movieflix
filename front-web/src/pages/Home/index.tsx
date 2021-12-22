import React from 'react';
import { ReactComponent as MainImage } from '../../core/images/main.svg';
import './style.scss';

const Home = () => (

  <div className = "home-container">
    <div className = "home-content">
    <h1 className = "home-title">Avalie Filmes</h1>          
      <div>
          <h3 className="home-subTitle">
              Diga o que você achou do seu filme favorito
          </h3>
      </div>
      <div className="home-image">
      <MainImage />
    </div>  
      </div>
  </div>


);

export default Home;