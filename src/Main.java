import javax.swing.*;  // دي مكتبة بتساعدنا نعمل نافذة GUI
import java.awt.*;      // دي مكتبة مسؤولة عن الرسم والألوان وكل اللي ليه علاقة بالجرافيكس
 class CircleDrawingPanel extends JPanel {
    // الدالة دي Java بتنده عليها لوحدها لما تيجي ترسم على الشاشة
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // دي بنكتبها دايمًا في الأول علشان نرسم فوق اللوحة الأساسية
        // هنا بنحدد مركز الدائرة اللي عايزين نرسمها
        int centerX = 200;
        int centerY = 150;
        // بنحدد كمان  طول نصف القطر بتاع الدائرة
        int radius = 100;
        // بنبدأ من أول نقطة فوق في الدائرة (يعني على محور Y)
        int currX = 0;
        int currY = radius;

        // ده متغير بنسميه "معامل القرار" أو Decision Parameter
        // وده اللي بيحدد هنمشي إزاي، نروح يمين بس؟ ولا ننزل كمان؟
        int decision = 1 - radius;

        // نحدد لون النقاط اللي هنرسمها
        g.setColor(Color.BLUE);  // أختارته ازرق

        // نرسم أول مجموعة من النقط حوالين المركز (الدائرة متماثلة في 8 اتجاهات)
        plotSymmetricPoints(g, centerX, centerY, currX, currY);

        // نبدأ نلف حوالين الدائرة ونرسم باقي النقاط
        while (currX < currY) {
            currX++; // نزود x كل مرة

            // دلوقتي بقى نشوف القرار:
            if (decision < 0) {
                // لو اقل من صفر، نتحرك يمين بس
                decision += 2 * currX + 1;
            } else {
                // لو اكبر أو يساوي صفر، نتحرك يمين وتحت كمان
                currY--;
                decision += 2 * (currX - currY) + 1;
            }

            // نرسم النقط الجديدة حوالين الدايرة
            plotSymmetricPoints(g, centerX, centerY, currX, currY);
        }
    }

    // دي دالة بتساعدنا نرسم الـ 8 نقط المتماثلين حوالين مركز الدائرة
    private void plotSymmetricPoints(Graphics g, int cx, int cy, int x, int y) {
        // كل سطر من دول بيرسم نقطة صغيرة (1 بيكسل × 1 بيكسل)
        g.drawRect(cx + x, cy + y, 1, 1); // يمين تحت
        g.drawRect(cx - x, cy + y, 1, 1); // شمال تحت
        g.drawRect(cx + x, cy - y, 1, 1); // يمين فوق
        g.drawRect(cx - x, cy - y, 1, 1); // شمال فوق

        g.drawRect(cx + y, cy + x, 1, 1); // تحت يمين
        g.drawRect(cx - y, cy + x, 1, 1); // تحت شمال
        g.drawRect(cx + y, cy - x, 1, 1); // فوق يمين
        g.drawRect(cx - y, cy - x, 1, 1); // فوق شمال
    }

    // ال main
    public static void main(String[] args) {
        //نعمل frame
        JFrame window = new JFrame("Circle Drawing Example");

        // نعمل اللوحة اللي هنرسم عليها
        CircleDrawingPanel canvas = new CircleDrawingPanel();

        // نضيف اللوحة دي جوا ال frame
        window.add(canvas);

        // نحدد حجم ال frame
        window.setSize(400, 350);

        // عملت ديه عشان لما اقفل الwindowيتقفل ال run بتاع ال program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // نخلي windowتظهر على الشاشة
        window.setVisible(true);
    }
}
