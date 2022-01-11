import { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { isAuthenticated, logout } from '../../utils/auth'
import './style.scss'

const Navbar = () => {
  const [isUserLogged, setIsUserLogged] = useState({})
  const location = useLocation()

  useEffect(() => {
    const userLogged = isAuthenticated()
    setIsUserLogged(userLogged)
  }, [location])

  return (
    <nav className="navbar-container">
      <div className="navbar-content">
        <Link to="/movies" className="navbar-logo">MovieFlix</Link>
        {isUserLogged && (
          <div
            className="navbar-logout-button"
            onClick={ logout }
          >
            <Link to= "/" className="navbar-logout-button-text">
              Sair
            </Link>
          </div>
        )}
      </div>
    </nav>
  )
}

export default Navbar