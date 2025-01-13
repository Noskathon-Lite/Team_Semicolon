
import { React, useEffect, useState } from 'react';
import { Link } from 'react-router-dom'
// import { Row, Col } from 'react-bootstrap';
// import 'bootstrap/dist/css/bootstrap.css';
import './login.css';


import SignUp from '../Signup/Signup';






const Login = () => {
    const [input, setInput] = useState({
        username: '',
        password: '',
       

    });
    const [submit, setSubmit] = useState({ ...input });
    const [error, setError] = useState({ ...input });
   
    const [showSignUpModal, setShowSignUpModal] = useState(false);


    const handleSubmit = (e) => {

        e.preventDefault();
        setSubmit({
            isSubmitting: true,
            ...input

        });
        setInput('');
    };

    const handleChange = (e) => {
        setInput({
            ...input,
            [e.target.name]: e.target.value,
        });
        setError({
            ...error,
            [e.target.name]: e.target.value ? '' : 'Required Field',
        });
    };

    const handleOpenSignUpModal = () => {
        setShowSignUpModal(true);
      };
    
      const handleCloseSignUpModal = () => {
        setShowSignUpModal(false);
      };

   

    
    return (
        <>


            <header>
                <div className="container text-center">
                    <div className='row align-items-center'>
                        
                        <div className="col">
                            <form action="" method="post" onSubmit={handleSubmit}>
                                <div className="form_body ">
                                    <label htmlFor="" className='form-label'>
                                        Email:
                                    <input className="login-input" type="email" id='email' name="username" placeholder='Email ' value={input.username}
                                        onChange={handleChange} />
                                        </label>
                                        <label htmlFor="" className='form-label'>
                                        Password:
                                    <input className="login-input" type="password" id='password' name="password" placeholder='Password' value={input.password} onChange={handleChange} />
                                    </label>
                                    <div className='row  align-items-center'>
                                        <div className='col'>
                                            <Link to="/layout" >
                                                <button type='submit' className='login' >Login</button>
                                            </Link>
                                        </div>
                                       
                                    </div>
                                    
                                    <hr />
                                    <p>Don't have account?</p>

                                    <button onClick={handleOpenSignUpModal}>Create New Account</button>
                                    {submit ?<SignUp show={showSignUpModal} onClose={handleCloseSignUpModal} />: "provide valid login details" }
                                    {/* <SignUp show={showSignUpModal} onClose={handleCloseSignUpModal} /> */}

                                </div>
                            </form>
                        </div>

                    </div>
                </div >
            </header >
        </>
    )
}

export default Login;