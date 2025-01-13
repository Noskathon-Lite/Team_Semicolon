import React, { useRef, useState } from "react";
import './AlertModal.css'
import ReactModal from "react-modal";
import { format } from 'date-fns';

import OSM from '../Map/OSM'

ReactModal.setAppElement("#root");

function AlertModal({ show, onClose, alert }) {
  const videoRef = useRef(null); // Reference to the video element
  const [isPlaying, setIsPlaying] = useState(false); // Track play/pause state
  const [volume, setVolume] = useState(1);
  const [mapToggle, setMapToggle] = useState(false)
  const handleMapToggleChange = () => {
    setMapToggle(prevState => !prevState)
  }

  const handlePlayPause = () => {
    if (isPlaying) {
      videoRef.current.pause(); // Pause the video
    } else {
      videoRef.current.play(); // Play the video
    }
    setIsPlaying(!isPlaying);
  };

  const handleVolumeChange = (e) => {
    const newVolume = e.target.value;
    videoRef.current.volume = newVolume; // Update video volume
    setVolume(newVolume);
  };

  const handleReplay = () => {
    videoRef.current.currentTime = 0; // Restart the video
    videoRef.current.play(); // Play the video from the beginning
    setIsPlaying(true);
  };

  return (

    <ReactModal
      isOpen={show}
      onRequestClose={onClose}
      contentLabel="Alert Detail"
      className="modal-content"
      overlayClassName="modal-overlay"
    >
        <div className="alert-modal-wrapper">
        {
            alert && (
                <div className="alert-modal">
                    <div className="details-map-container">
                        <div className="alert-details">
                            <h3>Sender Info</h3>
                            <div className="details">
                                <div className="side-details">
                                    <span>Name: <span style={{fontWeight:"bold"}}>Jyoti Jyoshi</span></span>
                                    <span>Time: <span style={{fontWeight:"bold"}}>12:00 AM</span></span>
                                </div>
                                <div className="side-details">
                                    <span>Phone: <span style={{fontWeight:"bold"}}>9840989641</span></span>
                                    <span>Date: <span style={{fontWeight:"bold"}}>12 Jan, 2025</span></span>
                                </div>
                            </div>
                            
                        </div>
                        <div className="map-wrapper">
                            <h3>Incident Location</h3>
                            <div className="map">
                                <OSM/>
                            </div>
                        </div>
                    </div>
                    <div className="video-player-container">
                        <video
                            ref={videoRef}
                            className="video-player"
                            controls={false} // Custom controls
                        >
                            <source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4" />
                            Your browser does not support the video tag.
                        </video>

                        {/* Controls */}
                        <div className="video-controls">
                            <button onClick={handlePlayPause} className="video-button">
                                {isPlaying ? "Pause" : "Play"}
                            </button>
                            <button onClick={handleReplay} className="video-button">
                                Replay
                            </button>

                            <label className="volume-label">Volume:</label>
                            <input
                                type="range"
                                className="volume-slider"
                                min="0"
                                max="1"
                                step="0.1"
                                value={volume}
                                onChange={handleVolumeChange}
                            />
                        </div>
                    </div>
                    
                </div>
            )
        }
        
    </div>

    </ReactModal>
    
    
  )
}

export default AlertModal