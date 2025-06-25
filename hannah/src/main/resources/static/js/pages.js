// Hiệu ứng tab cho phần khóa học
document.querySelectorAll(".tab-btn").forEach((btn) => {
  btn.addEventListener("click", function () {
    document
      .querySelectorAll(".tab-btn")
      .forEach((b) => b.classList.remove("active"));
    this.classList.add("active");

    // Gán hiệu ứng xuất hiện lần lượt cho các thẻ khóa học
    const cards = document.querySelectorAll(".course-card");
    cards.forEach((card, index) => {
      card.style.animation = "none";
      setTimeout(() => {
        card.style.animation = `fadeInUp 0.6s ease-out ${
          index * 0.1
        }s forwards`;
      }, 10);
    });
  });
});
// Hiệu ứng hover cho thẻ khóa học
document.querySelectorAll(".course-card").forEach((card) => {
  card.addEventListener("mouseenter", function () {
    this.style.transform = "translateY(-10px) scale(1.02)";
  });
  card.addEventListener("mouseleave", function () {
    this.style.transform = "translateY(0) scale(1)";
  });
});
// Observer cho phần tử có animation (stats-section)
const observerOptions = {
  threshold: 0.5,
  rootMargin: "0px 0px -100px 0px",
};

const observer = new IntersectionObserver((entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      const numbers = entry.target.querySelectorAll(".stat-number");
      numbers.forEach((number) => {
        const finalNumber = number.textContent;
        const numericValue = parseInt(finalNumber.replace(/[^\d]/g, ""));
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
      });
      observer.unobserve(entry.target);
    }
  });
}, observerOptions);

const statsSection = document.querySelector(".stats-section");
if (statsSection) {
  observer.observe(statsSection);
}

// Cuộn mượt khi click các liên kết nội trang
document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();
    const target = document.querySelector(this.getAttribute("href"));
    if (target) {
      target.scrollIntoView({ behavior: "smooth", block: "start" });
    }
  });
});
function filterByType(type) {
  // Remove active class from all filter items
  document.querySelectorAll(".filter-item").forEach((item) => {
    item.classList.remove("active");
  });
  // Add active class to clicked item
  event.target.closest(".filter-item").classList.add("active");

  console.log("Filtering by type:", type);
  // Here you would implement the actual filtering logic
}

function filterByTopic(topic) {
  console.log("Filtering by topic:", topic);
  // Implement topic filtering
}

function filterByLevel(level) {
  console.log("Filtering by level:", level);
  // Implement level filtering
}

function sortBy(criteria) {
  // Remove active class from all tabs
  document.querySelectorAll(".filter-tab").forEach((tab) => {
    tab.classList.remove("active");
  });

  // Add active class to clicked tab
  event.target.classList.add("active");

  console.log("Sorting by:", criteria);
  // Implement sorting logic
}

function viewDocument(docId) {
  console.log("Viewing document:", docId);
  // Implement document viewing
}

function downloadDocument(event, docId) {
  event.stopPropagation();
  alert(`Đang tải xuống tài liệu: ${docId}`);
  // Implement download logic
}

function previewDocument(event, docId) {
  event.stopPropagation();
  alert(`Đang mở xem trước tài liệu: ${docId}`);
  // Implement preview logic
}

// Search functionality
document.querySelector(".search-btn").addEventListener("click", function () {
  const query = document.querySelector(".search-input").value.trim();
  if (query) {
    console.log("Searching for:", query);
    // Implement search logic
  }
});

document
  .querySelector(".search-input")
  .addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
      document.querySelector(".search-btn").click();
    }
  });

// Add hover effects to document cards
document.querySelectorAll(".document-card").forEach((card) => {
  card.addEventListener("mouseenter", function () {
    this.style.transform = "translateY(-10px) scale(1.02)";
  });

  card.addEventListener("mouseleave", function () {
    this.style.transform = "translateY(0) scale(1)";
  });
});
function createPost(event) {
  event.preventDefault();
  const textarea = event.target.querySelector(".post-input");
  if (textarea.value.trim()) {
    alert("Bài đăng đã được tạo thành công!");
    textarea.value = "";
  } else {
    alert("Vui lòng nhập nội dung bài đăng!");
  }
  return false;
}

function toggleLike(button) {
  if (button.classList.contains("liked")) {
    button.classList.remove("liked");
    button.innerHTML = '<i class="fas fa-heart"></i> Thích';
  } else {
    button.classList.add("liked");
    button.innerHTML = '<i class="fas fa-heart"></i> Đã thích';
  }
}

// Add hover effects to posts
document.querySelectorAll(".post-item").forEach((post) => {
  post.addEventListener("mouseenter", function () {
    this.style.transform = "translateY(-5px)";
  });

  post.addEventListener("mouseleave", function () {
    this.style.transform = "translateY(0)";
  });
});

// Add click effects to trending items
document.querySelectorAll(".trending-item").forEach((item) => {
  item.addEventListener("click", function () {
    alert(
      `Hiển thị tất cả bài đăng về ${
        this.querySelector(".trending-title").textContent
      }`
    );
  });
});

// Kiểm tra user role và hiển thị UI phù hợp
document.addEventListener("DOMContentLoaded", function () {
  // Giả lập kiểm tra user đã đăng nhập
  const userRole = localStorage.getItem("userRole") || "guest";
  const userProfile = document.getElementById("userProfile");
  const authButtons = document.getElementById("authButtons");

  if (userRole === "teacher") {
    userProfile.style.display = "flex";
    authButtons.style.display = "none";
  } else if (userRole === "student") {
    userProfile.innerHTML = `
                    <div class="user-badge">
                        <i class="fas fa-user-graduate"></i>
                        Học viên
                    </div>
                    <div class="user-avatar">HV</div>
                    <div class="user-name" onclick="window.location.href='user-profile.html'" style="cursor: pointer;">Nguyễn Văn B</div>
                `;
    userProfile.style.display = "flex";
    authButtons.style.display = "none";
  }
});
function createPost(event) {
  event.preventDefault();
  const textarea = event.target.querySelector(".post-input");
  if (textarea.value.trim()) {
    alert("Bài đăng đã được tạo thành công!");
    textarea.value = "";
  } else {
    alert("Vui lòng nhập nội dung bài đăng!");
  }
  return false;
}

function toggleLike(button) {
  if (button.classList.contains("liked")) {
    button.classList.remove("liked");
    button.innerHTML = '<i class="fas fa-heart"></i> Thích';
  } else {
    button.classList.add("liked");
    button.innerHTML = '<i class="fas fa-heart"></i> Đã thích';
  }
}

// Add hover effects to posts
document.querySelectorAll(".post-item").forEach((post) => {
  post.addEventListener("mouseenter", function () {
    this.style.transform = "translateY(-5px)";
  });

  post.addEventListener("mouseleave", function () {
    this.style.transform = "translateY(0)";
  });
});

// Add click effects to trending items
document.querySelectorAll(".trending-item").forEach((item) => {
  item.addEventListener("click", function () {
    alert(
      `Hiển thị tất cả bài đăng về ${
        this.querySelector(".trending-title").textContent
      }`
    );
  });
});

// Kiểm tra user role và hiển thị UI phù hợp
document.addEventListener("DOMContentLoaded", function () {
  // Giả lập kiểm tra user đã đăng nhập
  const userRole = localStorage.getItem("userRole") || "guest";
  const userProfile = document.getElementById("userProfile");
  const authButtons = document.getElementById("authButtons");

  if (userRole === "teacher") {
    userProfile.style.display = "flex";
    authButtons.style.display = "none";
  } else if (userRole === "student") {
    userProfile.innerHTML = `
                    <div class="user-badge">
                        <i class="fas fa-user-graduate"></i>
                        Học viên
                    </div>
                    <div class="user-avatar">HV</div>
                    <div class="user-name" onclick="window.location.href='user-profile.html'" style="cursor: pointer;">Nguyễn Văn B</div>
                `;
    userProfile.style.display = "flex";
    authButtons.style.display = "none";
  }
});
// Add scroll animations
const scrollObserverOptions = {
  threshold: 0.1,
  rootMargin: "0px 0px -50px 0px",
};

const scrollObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      entry.target.style.opacity = "1";
      entry.target.style.transform = "translateY(0)";
    }
  });
}, scrollObserverOptions);

// Observe all animated elements
document
  .querySelectorAll(".value-card, .team-card, .timeline-item")
  .forEach((el) => {
    el.style.opacity = "0";
    el.style.transform = "translateY(30px)";
    el.style.transition = "all 0.6s ease-out";
    scrollObserver.observe(el);
  });

// Add hover effects to team cards
document.querySelectorAll(".team-card").forEach((card) => {
  card.addEventListener("mouseenter", function () {
    this.style.transform = "translateY(-10px) scale(1.02)";
  });

  card.addEventListener("mouseleave", function () {
    this.style.transform = "translateY(0) scale(1)";
  });
});

// Animate stats numbers
function animateStats() {
  const statNumbers = document.querySelectorAll(".stat-number");
  statNumbers.forEach((stat) => {
    const target = parseInt(stat.textContent.replace(/[^0-9]/g, ""));
    let current = 0;
    const increment = target / 100;
    const timer = setInterval(() => {
      current += increment;
      if (current >= target) {
        current = target;
        clearInterval(timer);
      }
      stat.textContent =
        Math.floor(current).toLocaleString() +
        (stat.textContent.includes("%") ? "%" : "+");
    }, 20);
  });
}

// Trigger stats animation when stats section is visible
const statsObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      animateStats();
      statsObserver.unobserve(entry.target);
    }
  });
});

statsObserver.observe(document.querySelector(".stats-section"));

let isPlaying = false;

function playVideo() {
  const playButton = document.querySelector(".play-button");
  const controlBtn = document.querySelector(".control-btn i");

  if (!isPlaying) {
    playButton.style.display = "none";
    controlBtn.classList.remove("fa-play");
    controlBtn.classList.add("fa-pause");
    isPlaying = true;

    // Simulate video progress
    simulateVideoProgress();
  }
}

function togglePlay() {
  const playButton = document.querySelector(".play-button");
  const controlBtn = document.querySelector(".control-btn i");

  if (isPlaying) {
    playButton.style.display = "flex";
    controlBtn.classList.remove("fa-pause");
    controlBtn.classList.add("fa-play");
    isPlaying = false;
  } else {
    playButton.style.display = "none";
    controlBtn.classList.remove("fa-play");
    controlBtn.classList.add("fa-pause");
    isPlaying = true;
    simulateVideoProgress();
  }
}

function simulateVideoProgress() {
  if (!isPlaying) return;

  const progressFill = document.querySelector(".progress-fill");
  let currentWidth = parseInt(progressFill.style.width) || 25;

  if (currentWidth < 100) {
    progressFill.style.width = currentWidth + 1 + "%";
    setTimeout(simulateVideoProgress, 1000);
  }
}

function seekVideo(event) {
  const progressBar = event.currentTarget;
  const rect = progressBar.getBoundingClientRect();
  const clickX = event.clientX - rect.left;
  const percentage = (clickX / rect.width) * 100;

  document.querySelector(".progress-fill").style.width = percentage + "%";
}

function switchTab(tabName) {
  // Remove active class from all tabs and panels
  document
    .querySelectorAll(".tab-btn")
    .forEach((btn) => btn.classList.remove("active"));
  document
    .querySelectorAll(".tab-panel")
    .forEach((panel) => panel.classList.remove("active"));

  // Add active class to clicked tab and corresponding panel
  event.target.classList.add("active");
  document.getElementById(tabName).classList.add("active");
}

function downloadDocument(type) {
  alert(`Đang tải xuống tài liệu: ${type}`);
}

function postComment() {
  const textarea = document.querySelector(".comment-form textarea");
  if (textarea.value.trim()) {
    alert("Đã gửi câu hỏi! Giáo viên sẽ trả lời sớm nhất có thể.");
    textarea.value = "";
  }
}

// Auto-play simulation
window.addEventListener("load", function () {
  setTimeout(() => {
    const progressFill = document.querySelector(".progress-fill");
    progressFill.style.width = "25%";
  }, 500);
});
function toggleDropdown() {
  const dropdown = document.getElementById("userDropdown");
  dropdown.style.display =
    dropdown.style.display === "block" ? "none" : "block";
}

// Tắt dropdown khi click ngoài vùng
document.addEventListener("click", function (e) {
  const dropdown = document.getElementById("userDropdown");
  const trigger = document.querySelector(".user-dropdown");
  if (!trigger.contains(e.target)) {
    dropdown.style.display = "none";
  }
});

function logout() {
  if (confirm("Bạn có chắc chắn muốn đăng xuất?")) {
    alert("Bạn đã đăng xuất!");
    window.location.href = "login.html";
  }
}
        // Animate progress bars on load
        window.addEventListener('load', function() {
            const progressBars = document.querySelectorAll('.progress-fill');
            progressBars.forEach((bar, index) => {
                setTimeout(() => {
                    const width = bar.style.width;
                    bar.style.width = '0%';
                    setTimeout(() => {
                        bar.style.width = width;
                    }, 100);
                }, index * 200);
            });
        });

        // Add click animations to cards
        document.querySelectorAll('.course-item').forEach(item => {
            item.addEventListener('click', function(e) {
                if (!e.target.closest('.btn')) {
                    this.style.transform = 'scale(0.98)';
                    setTimeout(() => {
                        this.style.transform = 'translateY(-2px)';
                    }, 150);
                }
            });
        });