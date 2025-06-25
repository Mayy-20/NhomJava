
// Biến global cho chatbox

let chatOpen = false;
let chatWindow,
  chatToggle,
  chatIcon,
  chatMessages,
  chatInput,
  sendBtn,
  typingIndicator;
// Câu trả lời mẫu theo chủ đề
const aiResponses = {
  greetings: [
    "Xin chào! Tôi là Hannah AI. Hôm nay tôi có thể giúp gì cho bạn?",
    "Chào bạn! Rất vui được hỗ trợ bạn trong hành trình học lập trình.",
    "Hi! Tôi là Hannah, trợ lý AI của bạn. Cần tư vấn gì không?",
  ],
  programming: [
    "Tuyệt vời! Lập trình là một kỹ năng rất quan trọng hiện nay. Bạn muốn bắt đầu với ngôn ngữ nào? Tôi khuyên bạn nên học Python trước - nó dễ học và có nhiều ứng dụng.",
    "Học lập trình là lựa chọn thông minh! Tôi gợi ý bạn bắt đầu với HTML/CSS để hiểu web, sau đó học JavaScript. Bạn có muốn tôi tư vấn lộ trình chi tiết không?",
    "Lập trình mở ra nhiều cơ hội nghề nghiệp! Hãy cho tôi biết bạn quan tâm đến lĩnh vực nào: Web Development, Data Science, hay Mobile App?",
  ],
  courses: [
    "Dựa trên kinh nghiệm của tôi, tôi khuyên bạn:\n• Người mới: Bắt đầu với Python hoặc HTML/CSS\n• Có kinh nghiệm: JavaScript hoặc React.js\n• Thích dữ liệu: SQL và Machine Learning\n\nBạn thuộc nhóm nào?",
    "Chúng tôi có nhiều khóa học phù hợp với từng level:\n🔰 Beginner: Python, HTML/CSS\n📈 Intermediate: JavaScript, SQL  \n🚀 Advanced: React.js, Machine Learning\n\nBạn muốn tìm hiểu khóa nào cụ thể?",
    "Tôi sẽ giúp bạn chọn khóa học phù hợp! Hãy cho tôi biết:\n• Bạn đã có kinh nghiệm lập trình chưa?\n• Bạn muốn làm việc trong lĩnh vực nào?\n• Bạn có bao nhiều thời gian để học mỗi ngày?",
  ],
  roadmap: [
    "Đây là lộ trình học tôi khuyên:\n\n🎯 Giai đoạn 1 (1-2 tháng):\n• Học Python cơ bản\n• Làm quen với logic lập trình\n\n🎯 Giai đoạn 2 (2-3 tháng):\n• HTML/CSS cho web\n• JavaScript cơ bản\n\n🎯 Giai đoạn 3 (3-4 tháng):\n• Framework như React\n• Database với SQL\n\nBạn muốn tôi giải thích chi tiết giai đoạn nào?",
    "Lộ trình học hiệu quả:\n\n📚 Tuần 1-4: Nền tảng\n• Python hoặc JavaScript\n• Cú pháp và logic cơ bản\n\n📚 Tuần 5-12: Thực hành\n• Làm project nhỏ\n• Học HTML/CSS\n\n📚 Tuần 13-24: Nâng cao\n• Framework và Library\n• Database và API\n\nBạn có muốn bắt đầu ngay không?",
  ],
  help: [
    "Tôi có thể giúp bạn:\n• 🎯 Tư vấn khóa học phù hợp\n• 📖 Giải đáp thắc mắc lập trình\n• 🗺️ Lập lộ trình học tập\n• 💡 Gợi ý project thực hành\n• 🚀 Hướng dẫn phát triển career\n\nBạn cần hỗ trợ gì cụ thể?",
    "Tôi ở đây để hỗ trợ bạn!\n\n✅ Có thể hỏi tôi về:\n• Cách chọn ngôn ngữ lập trình\n• Lộ trình học từ zero đến hero\n• Kinh nghiệm học tập hiệu quả\n• Cơ hội nghề nghiệp trong IT\n\nHãy đặt câu hỏi, tôi sẽ trả lời ngay!",
  ],
  default: [
    "Câu hỏi thú vị! Tôi hiểu bạn đang quan tâm đến lập trình. Bạn có thể hỏi tôi về khóa học, lộ trình học, hoặc bất kỳ thắc mắc nào về công nghệ.",
    "Tôi chưa hiểu rõ câu hỏi của bạn. Bạn có thể hỏi tôi về:\n• Tư vấn khóa học lập trình\n• Lộ trình học từ cơ bản đến nâng cao\n• Kinh nghiệm học tập hiệu quả\n\nHãy thử hỏi cụ thể hơn nhé!",
    "Hmm, tôi cần hiểu rõ hơn để giúp bạn tốt nhất. Bạn có thể đặt câu hỏi về học lập trình, chọn khóa học, hay lộ trình phát triển không?",
  ],
};
// Hàm global có thể gọi từ HTML
function toggleChat() {
  chatOpen = !chatOpen;
  if (chatOpen) {
    chatWindow.classList.add("active");
    chatIcon.textContent = "✕";
    chatInput.focus();
  } else {
    chatWindow.classList.remove("active");
    chatIcon.textContent = "💬";
  }
}

function sendQuickReply(message) {
  if (!chatMessages) return; // Kiểm tra xem chatbox đã được khởi tạo chưa

  addMessage(message, false, true);
  showTyping();
  setTimeout(() => {
    hideTyping();
    const aiResponse = getAIResponse(message);
    addMessage(aiResponse, true);
  }, 800 + Math.random() * 1200);
}
// Hàm helper
function addMessage(content, isBot = true, isQuick = false) {
  const messageDiv = document.createElement("div");
  messageDiv.className = `message ${isBot ? "bot" : "user"}`;

  messageDiv.innerHTML = `
    <div class="message-avatar">${isBot ? "H" : "U"}</div>
    <div class="message-content">${content.replace(/\n/g, "<br>")}</div>
  `;

  chatMessages.appendChild(messageDiv);
  chatMessages.scrollTop = chatMessages.scrollHeight;

  const quickReplies = document.querySelector(".quick-replies");
  if (quickReplies) {
    if (!isQuick && !isBot) {
      quickReplies.style.display = "none";
    } else if (isQuick) {
      quickReplies.style.display = "";
    }
  }
}

function showTyping() {
  typingIndicator.classList.add("active");
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

function hideTyping() {
  typingIndicator.classList.remove("active");
}

function getAIResponse(userMessage) {
  const message = userMessage.toLowerCase();
  if (
    message.includes("chào") ||
    message.includes("hello") ||
    message.includes("hi")
  ) {
    return aiResponses.greetings[
      Math.floor(Math.random() * aiResponses.greetings.length)
    ];
  }
  if (
    message.includes("lập trình") ||
    message.includes("programming") ||
    message.includes("code")
  ) {
    return aiResponses.programming[
      Math.floor(Math.random() * aiResponses.programming.length)
    ];
  }
  if (
    message.includes("khóa học") ||
    message.includes("course") ||
    message.includes("phù hợp")
  ) {
    return aiResponses.courses[
      Math.floor(Math.random() * aiResponses.courses.length)
    ];
  }
  if (
    message.includes("lộ trình") ||
    message.includes("roadmap") ||
    message.includes("học như thế nào")
  ) {
    return aiResponses.roadmap[
      Math.floor(Math.random() * aiResponses.roadmap.length)
    ];
  }
  if (
    message.includes("giúp") ||
    message.includes("help") ||
    message.includes("hỗ trợ")
  ) {
    return aiResponses.help[
      Math.floor(Math.random() * aiResponses.help.length)
    ];
  }
  return aiResponses.default[
    Math.floor(Math.random() * aiResponses.default.length)
  ];
}

function sendMessage() {
  const message = chatInput.value.trim();
  if (!message) return;

  addMessage(message, false);
  chatInput.value = "";
  sendBtn.disabled = true;
  showTyping();

  setTimeout(() => {
    hideTyping();
    const aiResponse = getAIResponse(message);
    addMessage(aiResponse, true);
    sendBtn.disabled = false;
  }, 1000 + Math.random() * 2000);
}


// Khởi tạo chatbox sau khi DOM đã sẵn sàng
document.addEventListener("DOMContentLoaded", function () {
  // Khởi tạo các biến DOM
  chatWindow = document.getElementById("chatWindow");
  chatToggle = document.getElementById("chatToggle");
  chatIcon = document.getElementById("chatIcon");
  chatMessages = document.getElementById("chatMessages");
  chatInput = document.getElementById("chatInput");
  sendBtn = document.getElementById("sendBtn");
  typingIndicator = document.getElementById("typingIndicator");

  // Kiểm tra nếu các phần tử chatbox tồn tại
  if (
    !chatWindow ||
    !chatToggle ||
    !chatIcon ||
    !chatMessages ||
    !chatInput ||
    !sendBtn ||
    !typingIndicator
  ) {
    console.warn(
      "One or more chatbox elements not found. Chatbox functionality might be limited or disabled."
    );
    return;
  }

  // Gắn sự kiện
  chatInput.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
      sendMessage();
    }
  });

  chatInput.addEventListener("input", function () {
    sendBtn.disabled = this.value.trim() === "";
  });

  sendBtn.addEventListener("click", sendMessage);
  sendBtn.disabled = true;
});
