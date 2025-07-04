document.addEventListener("DOMContentLoaded", function () {
    // Hàm xử lý tabs
    function initTabs() {
        const tabButtons = document.querySelectorAll(".tab-btn");
        if (tabButtons.length === 0) return;

        tabButtons.forEach((btn) => {
            btn.addEventListener("click", function () {
                tabButtons.forEach((b) => b.classList.remove("active"));
                this.classList.add("active");

                const cards = document.querySelectorAll(".course-card");
                if (cards.length > 0) {
                    cards.forEach((card, index) => {
                        card.style.animation = "none";
                        setTimeout(() => {
                            card.style.animation = `fadeInUp 0.6s ease-out ${index * 0.1}s forwards`;
                        }, 10);
                    });
                }
            });
        });
    }

    // Hàm xử lý hover cho các loại card
    function initHoverEffects(selector, hoverTransform, leaveTransform) {
        const elements = document.querySelectorAll(selector);
        if (elements.length === 0) return;

        elements.forEach((element) => {
            element.addEventListener("mouseenter", function () {
                this.style.transform = hoverTransform;
            });
            element.addEventListener("mouseleave", function () {
                this.style.transform = leaveTransform;
            });
        });
    }

    // Hàm xử lý animation cho stats section
    function initStatsAnimation() {
        const statsSection = document.querySelector(".stats-section");
        if (!statsSection) return;

        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        const numbers = entry.target.querySelectorAll(".stat-number");
                        if (numbers.length === 0) {
                            console.warn("No .stat-number elements found in stats-section");
                            return;
                        }

                        numbers.forEach((number) => {
                            try {
                                const finalNumber = number.textContent;
                                const numericValue = parseInt(finalNumber.replace(/[^\d]/g, ""));
                                if (isNaN(numericValue)) {
                                    console.warn(`Invalid number format in stat-number: ${finalNumber}`);
                                    return;
                                }

                                let currentNumber = 0;
                                const increment = numericValue / 50;

                                const timer = setInterval(() => {
                                    currentNumber += increment;
                                    if (currentNumber >= numericValue) {
                                        number.textContent = finalNumber;
                                        clearInterval(timer);
                                    } else {
                                        number.textContent =
                                            Math.floor(currentNumber).toLocaleString() +
                                            (finalNumber.includes("+") ? "+" : "") +
                                            (finalNumber.includes("%") ? "%" : "");
                                    }
                                }, 30);
                            } catch (error) {
                                console.error(`Error animating stat-number: ${error.message}`);
                            }
                        });
                        observer.unobserve(entry.target);
                    }
                });
            },
            { threshold: 0.5, rootMargin: "0px 0px -100px 0px" }
        );

        observer.observe(statsSection);
    }

    // Hàm xử lý smooth scroll
    function initSmoothScroll() {
        const anchors = document.querySelectorAll('a[href^="#"]');
        if (anchors.length === 0) return;

        anchors.forEach((anchor) => {
            anchor.addEventListener("click", function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute("href"));
                if (target) {
                    target.scrollIntoView({ behavior: "smooth", block: "start" });
                } else {
                    console.warn(`Smooth scroll target not found: ${this.getAttribute("href")}`);
                }
            });
        });
    }

    // Hàm xử lý tìm kiếm
    function initSearch() {
        const searchBtn = document.querySelector(".search-btn");
        const searchInput = document.querySelector(".search-input");
        if (!searchBtn || !searchInput) return;

        searchBtn.addEventListener("click", function () {
            const query = searchInput.value.trim();
            if (query) {
                console.log("Searching for:", query);
                // Thêm logic tìm kiếm tại đây
            }
        });

        searchInput.addEventListener("keypress", function (e) {
            if (e.key === "Enter") {
                searchBtn.click();
            }
        });
    }

    // Hàm xử lý click trên trending items
    function initTrendingItems() {
        const trendingItems = document.querySelectorAll(".trending-item");
        if (trendingItems.length === 0) return;

        trendingItems.forEach((item) => {
            item.addEventListener("click", function () {
                const title = this.querySelector(".trending-title")?.textContent;
                if (title) {
                    alert(`Hiển thị tất cả bài đăng về ${title}`);
                } else {
                    console.warn("No trending-title found in trending-item");
                }
            });
        });
    }

    // Hàm xử lý scroll animation cho các phần tử
    function initScrollsAnimation() {
        const scrollElements = document.querySelectorAll(".value-card, .team-card, .timeline-item");
        if (scrollElements.length === 0) return;

        const scrollObserver = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.style.opacity = "1";
                        entry.target.style.transform = "translateY(0)";
                    }
                });
            },
            { threshold: 0.1, rootMargin: "0px 0px -50px 0px" }
        );

        scrollElements.forEach((el) => {
            el.style.opacity = "0";
            el.style.transform = "translateY(30px)";
            el.style.transition = "all 0.6s ease-out";
            scrollObserver.observe(el);
        });
    }

    // Hàm xử lý animation cho progress bars
    function initProgressBars() {
        const progressBars = document.querySelectorAll(".progress-fill");
        if (progressBars.length === 0) return;

        progressBars.forEach((bar, index) => {
            setTimeout(() => {
                const width = bar.style.width || "0%";
                bar.style.width = "0%";
                setTimeout(() => {
                    bar.style.width = width;
                }, 100);
            }, index * 200);
        });
    }

    // Hàm xử lý click animation cho course items
    function initCourseItemClick() {
        const courseItems = document.querySelectorAll(".course-item");
        if (courseItems.length === 0) return;

        courseItems.forEach((item) => {
            item.addEventListener("click", function (e) {
                if (!e.target.closest(".btn")) {
                    this.style.transform = "scale(0.98)";
                    setTimeout(() => {
                        this.style.transform = "translateY(-2px)";
                    }, 150);
                }
            });
        });
    }

    // Khởi chạy tất cả các hàm
    try {
        initTabs();
        initHoverEffects(".course-card", "translateY(-10px) scale(1.02)", "translateY(0) scale(1)");
        initHoverEffects(".document-card", "translateY(-10px) scale(1.02)", "translateY(0) scale(1)");
        initHoverEffects(".post-item", "translateY(-5px)", "translateY(0)");
        initHoverEffects(".team-card", "translateY(-10px) scale(1.02)", "translateY(0) scale(1)");
        initStatsAnimation();
        initSmoothScroll();
        initSearch();
        initTrendingItems();
        initScrollsAnimation();
        initProgressBars();
        initCourseItemClick();
    } catch (error) {
        console.error("Error initializing page scripts:", error.message);
    }
});