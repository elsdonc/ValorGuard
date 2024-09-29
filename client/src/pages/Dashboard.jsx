import React, { useState, useEffect } from 'react';

const Dashboard = () => {
  const [user, setUser] = useState(null);
  const [valUser, setValUser] = useState('');
  const [valTag, setValTag] = useState('');
  const [number, setNumber] = useState('');
  const [accPartnerNumber, setAccPartnerNumber] = useState('');
  const [isEditable, setIsEditable] = useState(false);
  const [message, setMessage] = useState('');

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
    } catch {
        window.location = "/";
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      valUser,
      valTag,
      number,
      accPartnerNumber,
    };
    try {
      const response = await fetch('http://localhost:8080/api/user/saveData', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      const result = await response.json();

      if (result.status === "success") {
        setMessage('Data saved successfully!');
      } else {
        setMessage(`Error: ${result.message}`);
      }
    } catch (error) {
      setMessage('An error occurred during submission.');
      console.error('Submission error:', error);
    }
  };

  const handleEditToggle = () => {
    setIsEditable((prev) => !prev);
  };

  return (
    <div className="dashboard">
      <h2>Welcome, {user}!</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Valorant Username:
            <input
              type="text"
              value={valUser}
              onChange={(e) => setValUser(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Tag (e.g. #1234):
            <input
              type="text"
              value={valTag}
              onChange={(e) => setValTag(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Phone Number:
            <input
              type="tel"
              value={number}
              onChange={(e) => setNumber(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Accountability Partner's Phone Number:
            <input
              type="tel"
              value={accPartnerNumber}
              onChange={(e) => setAccPartnerNumber(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <button type="submit" disabled={!isEditable}>Submit</button>
      </form>
      <button onClick={handleEditToggle}>
        {isEditable ? 'Stop Editing' : 'Edit'}
      </button>
      {message && <p>{message}</p>}
    </div>
  );
};

export default Dashboard;
