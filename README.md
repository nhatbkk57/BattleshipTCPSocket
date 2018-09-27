# BattleshipTCPSocket
##Nhóm tác giả: Đặng Văn Đại - K57 ĐH Bách Khoa HN.

Trò chơi Battleship cho 2 người chơi trên mạng bằng giao thức socket TCP. Hai người chơi cần kết nối với nhau để bắt đầu trò chơi. Bản đồ
là 1 ma trận 10 x 10, mỗi người chơi bố trí ngẫu nhiên 5 con tàu thuộc 5 loại khác nhau và tất nhiên kích thước cũng khác nhau (tàu lớn nhất có kích
thước 5 x 1 và tàu nhỏ nhất có kích thước 2 x 1) trên bản đồ (chỉ xếp dọc hoặc ngang, không được xếp chéo). Nhiệm vụ của 2 người chơi là phải bắn
cháy hết tàu của đối phương. Trước khi bắt đầu chơi thì các người chơi phải chọn vị trí đặt các tàu bằng cách kéo thả các tàu và đặt vào đúng vùng
biển của mình và người chơi phải đặt đủ 5 loại tàu có trong giao diện và khi đã đặt đủ 5 con tàu thì người chơi mới có thể bắt đầu chơi game. Người nào
khởi tạo game trước sẽ được bắn trước, nếu bắn trúng đối phương thì sẽ được bắn tiếp phát nữa và cứ như thế cho đến khi bắn trượt thì lượt chơi
sẽ được chuyển cho đối phương. Cứ như vậy cho đến khi người nào bắn cháy hết tàu của đối phương trước thì sẽ là người chiến thắng. Trong khi 2
người chơi chơi với nhau thì họ cũng có thể chát để nói chuyện với nhau được.
