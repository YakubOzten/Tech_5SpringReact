// rfce
// React
import React from 'react'

//Router
import { Navigate, Route, Routes } from 'react-router-dom'

// Header Main Footer
import HeaderProject from './Components/HeaderProject'
import MainProject from './Components/MainProject'
import FooterProject from './Components/FooterProject'

// login
import Login from './Components/login/Login'

//register
import RegisterCreate from './Components/register/RegisterCreate'
import RegisterUpdate from './Components/register/RegisterUpdate'
import RegisterView from './Components/register/RegisterView'
import RegisterList from './Components/register/RegisterList'

// user
import User from './Components/user/User'


// FUNCTION COMPONENT
function RouterProject() {

    // RETURN
    return (
        <React.Fragment>
            <HeaderProject logo="fa-solid fa-cloud"></HeaderProject>


            {/* ROUTING */}
            {/* dark mode */}
            {/* dark mode:App-header */}
            <div className="container mt-5 App-header">
                <Routes>

                    {/* ROOT */}
                    <Route path="/" element={<MainProject />} />
                    <Route path="/index" element={<MainProject />} />

                    {/* LOGIN */}
                    <Route path="/login" element={<Login />} />

                    {/* REGISTER */}
                    <Route path="/register/create" element={<RegisterCreate />} />
                    <Route path="/register/list" element={<RegisterList />} />
                    <Route path="/register/view/:id" element={<RegisterView />} />
                    <Route path="/register/update/:id" element={<RegisterUpdate />} />

                    {/* USER PAGE */}
                    <Route path="/user" element={<User />} />

                    {/* Bad request */}
                    <Route path={"*"} element={<Navigate to={"/"} />} />

                </Routes>
            </div>

            {/* <FooterProject copy="&copy; Bütün Haklar saklıdır." /> */}
        </React.Fragment>
    ) //end return
} // end function 


// EXPORT
export default RouterProject