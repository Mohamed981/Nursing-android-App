import './Projects.css'
import ProjectFolder from "../Components/ProjectFolder/ProjectFolder";
import SideBar from "../Components/sidebar/sidebar";



const click=()=>{

}

// useEffect(() => {
//   const getProjects = async () => {
//     const tasksFromServer = await fetchProjects()
//     setProjects(tasksFromServer)
//   }

//   getTasks()
// }, [])

// const fetchProjects = async () => {
//   const res = await fetch('http://localhost:5000/Projects')
//   const data = await res.json()

//   return data
// }

const Projects = ({onAdd,projects}) => {
  
    return ( 
        
        <div className="content">
          <button className="btn" onClick={click}>click</button>
          <SideBar onAdd={onAdd}/>
          {projects.map((project,index)=>(
            <ProjectFolder key={index} project={project}/>
          ))}

       
        </div>
        
     );

}
 
export default Projects;