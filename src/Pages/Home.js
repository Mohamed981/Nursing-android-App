import React from 'react'
import SideBar from '../Components/sidebar/sidebar'

const Home = ({onAdd}) => {
    return (
        <div>
        <SideBar onAdd={onAdd}/>
            <h1 style={{padding: "200px"}}>Hello</h1>
        </div>
    )
}

export default Home
