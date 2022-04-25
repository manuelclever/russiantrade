var worldMap = document.getElementById("worldMap");
            var worldMapParent = worldMap.parentElement;
            const panzoom = Panzoom(worldMap, {contain: "outside", minScale: 1, maxScale: 18, step: 1.2, cursor: "crosshair"});

            worldMapParent.addEventListener("wheel", panzoom.zoomWithWheel);
            worldMapParent.addEventListener("mouseover", function() {document.body.style.cursor = "grabbing";})
            worldMapParent.addEventListener("mouseleave", resetCursor());

            function resetCursor() {
                document.getElementById("root").style.cursor = "default";
            }