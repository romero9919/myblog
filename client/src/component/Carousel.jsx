import React, { useState, useEffect, useRef } from 'react';
import "../style/slider.css"

const Carousel = ({ slides, speed }) => {

    const [visibleSlide, setVisibleSlide] = useState(1);
    const [hasTransitionClass, setHasTransitionClass] = useState(true);
    const [stateSlides, setStateSlides] = useState(slides);
    const [prevNextDisable, setPrevNextDisable] = useState(false);
    const intervalId = useRef(null);
    const wdtRef = useRef(null);
    const [width, setWidth] = useState(0);

    //구성요소가 마운트될 때 배열 양쪽 끝에 추가
    useEffect(()=>{
       const cloneSlides = [...slides];
       cloneSlides.unshift(cloneSlides[cloneSlides.length-1]);
       cloneSlides.push(cloneSlides[1]);
       setStateSlides(cloneSlides);
       setWidth( wdtRef.current.offsetWidth);
       start();
       calMargin();
    }, []);

    const calMargin = () => {
        return "-" + (visibleSlide * width) + "px";
    }

    //마지막 슬라이드에 오면 첫 번째로 루프백 (무한 슬라이드 구현)
    useEffect(()=>{
      if(visibleSlide === stateSlides.length -1){
         setPrevNextDisable(true);
         setTimeout(()=> {
           setHasTransitionClass(false);
           setVisibleSlide(1);
         }, 500);
      }
      
      if(visibleSlide === 1) {
         setTimeout(()=>{
            setHasTransitionClass(true);
         }, 500);
      }

      if(visibleSlide === 0) {
         setPrevNextDisable(true);
         setTimeout(()=>{
            setHasTransitionClass(false);
            setVisibleSlide(stateSlides.length - 2);
         }, 500);
      }

      if(visibleSlide === stateSlides.length -2) {
         setTimeout(()=>{
            setHasTransitionClass(true)
         }, 500);
      }

    }, [prevNextDisable])


    const start = () => {
        if(intervalId.current != null) {
            return;
        }
        intervalId.current = setInterval(()=>{
           setVisibleSlide(prVisibleSlide => {
               if(prVisibleSlide + 1 === stateSlides.length){
                  return 0;
               }
               return prVisibleSlide + 1;
           })
        }, speed);
    }

    return (
        <div className="slideContainer">
            <div className={"slideIndicator " + (hasTransitionClass ? ' transition' : '')}
              style={{ left: calMargin() }}> 
            {
                stateSlides.map((slide, index) => {
                    if(index === 0 || index === stateSlides.length-1){
                        return null;
                    }
                    return (
                        <div className="customSlide" ref={wdtRef} key={index}>
                            <h1>{slide.title}</h1>
                            {slide.content()}
                        </div>    
                    )
                })
            }
            </div> 
        </div>
    );
}

export default Carousel;