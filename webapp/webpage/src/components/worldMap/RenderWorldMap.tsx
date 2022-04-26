import React from 'react';
import WorldMap from './WorldMap';
import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";

export default function RenderWorldMap() {
    return (
        <TransformWrapper
                    initialScale={1}
                    initialPositionX={0}
                    initialPositionY={0}
                    panning={{velocityDisabled: true}}
                >
                    {({ zoomIn, zoomOut, resetTransform, ...rest }) => (
                    <React.Fragment>
                        <TransformComponent>
                            <WorldMap/>
                        </TransformComponent>
                    </React.Fragment>
                    )}
                </TransformWrapper>
    );
}