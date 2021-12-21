import './App.css';
import Projects from './Pages/Projects';
import Home from './Pages/Home';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { useState,useEffect } from 'react';

const App=()=> {
  
  const [projects,setProjects]=useState([])

  useEffect(() => {
    const getProjects = async ()=>{ 
     const projectsFromServer= await fetchProjects();
    setProjects(projectsFromServer)
  }
  getProjects()
  }, [])

  const fetchProjects = async ()=>{
    const res=await fetch('http://localhost:5000/projects')
    const data = await res.json()

    return data
  }
  const addProject = async (project) => {
    const res = await fetch('http://localhost:5000/projects', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify(project),
    })

    const data = await res.json()

    setProjects([...projects, data])

  }
 
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Home onAdd={addProject}/>}/>
          <Route path="/Projects" element={projects.length>0?(<Projects onAdd={addProject} projects={projects}/>)
          :('No projects to show')}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
