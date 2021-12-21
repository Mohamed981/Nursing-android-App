import { FaHome,FaTasks,FaFileCode,FaPeopleArrows} from "react-icons/fa";
import { CgAdd } from "react-icons/cg";
import Tippy from "@tippy.js/react";
import { Link } from "react-router-dom";
import 'tippy.js/dist/tippy.css';   
import './sidebar.css';
import Projects from "../../Pages/Projects";
import Home from "../../Pages/Home";
import { useState, useEffect } from "react";
import App from "../../App";
const SideBar = ({onAdd}) => {
  const [text, setText] = useState('');
  const [title, setTitle] = useState('');
  useEffect(()=>{
    let add=document.getElementById("ii")
    add.addEventListener("click", function() {
      document.querySelector('.bg-modal').style.display = "flex";
      document.body.classList.add("stop-scrolling");
    });
    document.querySelector('.close').addEventListener("click", function() {
      document.querySelector('.bg-modal').style.display = "none";
      document.body.classList.remove("stop-scrolling");
    });
  },[])
  const click=(e)=>{
    e.preventDefault()

    if (!text) {
      alert('Please add a project')
      return
    }

    onAdd({ text})
    setText('')
    document.querySelector('.bg-modal').style.display = "none"
    document.body.classList.remove("stop-scrolling");
  }
    return ( 
    <div className="sidebar-content">
      <div className="bg-modal">
	       <div className="modal-contents">
            <div className="close">+</div>
              <div className="columns">
              <div className="column">
                <p style={{  font:'2'}}>Template</p>
                <input id="template"
                  placeholder='C++'
                value={text}
                onChange={(e) => setText(e.target.value)}/>
                
              </div>
              <div className="column">
                <p style={{  font:'2'}}>Title</p>
                <input id="title" 
                placeholder='title'
                value={title}
                onChange={(e) => setTitle(e.target.value)}/>
           
                <button onClick={click}>click</button>
                </div>
              </div>
              
	      </div>
       </div>
       <nav className="navbar">
        <ul className="navbar-nav">
          <li className="logo">
            <a href="#" className="nav-link">
              <span className="link-text logo-text">Code</span>
              <svg
                aria-hidden="true"
                focusable="false"
                data-prefix="fad"
                data-icon="angle-double-right"
                role="img"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 448 512"
                className="svg-inline--fa fa-angle-double-right fa-w-14 fa-5x"
              >
                <g className="fa-group">
                  <path
                    fill="currentColor"
                    d="M224 273L88.37 409a23.78 23.78 0 0 1-33.8 0L32 386.36a23.94 23.94 0 0 1 0-33.89l96.13-96.37L32 159.73a23.94 23.94 0 0 1 0-33.89l22.44-22.79a23.78 23.78 0 0 1 33.8 0L223.88 239a23.94 23.94 0 0 1 .1 34z"
                    className="fa-secondary"
                  ></path>
                  <path
                    fill="currentColor"
                    d="M415.89 273L280.34 409a23.77 23.77 0 0 1-33.79 0L224 386.26a23.94 23.94 0 0 1 0-33.89L320.11 256l-96-96.47a23.94 23.94 0 0 1 0-33.89l22.52-22.59a23.77 23.77 0 0 1 33.79 0L416 239a24 24 0 0 1-.11 34z"
                    className="fa-primary"
                  ></path>
                </g>
              </svg>
            </a>
          </li>
          <li className="nav-item">
            <Link to="/" className="nav-link">
                <FaHome viewBox="0 -150 600 512" size={50} />
                <span className="link-text">Home</span>
            </Link>
          </li>

          <li className="nav-item">
            <a href="#" className="nav-link">
                <FaTasks viewBox="0 -150 600 512" size={40} />
                <span className="link-text">Tasks</span>
            </a>
          </li>

          <li className="nav-item">
            <Link to="/Projects" className="nav-link">
                <FaFileCode viewBox="0 -150 512 512" size={40} />
                <span className="link-text">Projects</span>
            </Link>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-link">
                <FaPeopleArrows viewBox="0 -100 600 512" size={40} />
                <span className="link-text">Teams</span>
            </a>
          </li>
        
          <li className="nav-item">
          <Tippy content='New Project'>
          <div className="nav-linkPlus" >
            <CgAdd id="ii" className="icon" size={40} onClick={()=>{<Home/>}}/>
            </div>
          </Tippy>
          </li>
          

        </ul>
      </nav>
        </div>
     );
}
 
export default SideBar;