import React, { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import LandingPage from "./pages/LandingPage";
import Dashboard from "./pages/Dashboard";

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    getUser();
  }, []);

  const getUser = async () => {
    try {
        let res = await fetch("http://localhost:8080/api/user", {
            method: "GET",
            credentials: "include"
        });
        let data = await res.json();
        if (data["name"] != null) {
          setUser(data["name"])
        }
    } catch (e) {
        res.error(e);
    }
  };
  
  const handleGoogleSignIn = async () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  return (
    <>
      <Routes>
        <Route
          path="/"
          element={
            <LandingPage handleSignIn={handleGoogleSignIn} user={user} />
          }
        />
        <Route path="/dashboard" element={<Dashboard user={user}/>}/>
      </Routes>
    </>
  );
}

export default App;
