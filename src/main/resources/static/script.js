    let selectedLevel = "";

    document.querySelectorAll(".level-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            document.querySelectorAll(".level-btn").forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
            selectedLevel = btn.dataset.level;

            let texto = "";
            if (selectedLevel === "beginner") texto = "The dog is playing in the park.";
            if (selectedLevel === "intermediate") texto = "She continued working despite the unexpected challenges.";
            if (selectedLevel === "advanced") texto = "Human creativity flourishes when curiosity is nurtured and freedom is encouraged.";


            const ta = document.getElementById("textoIngles");
            ta.value = texto;

        });
    });