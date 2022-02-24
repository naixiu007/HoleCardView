package com.ctop007.holeCardView

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.ctop007.holeCard.HoleCardView
import com.ctop007.holeCardView.databinding.ActivityEffectBinding

/**
 * @author topc
 * @date 2022/2/23 11:40 下午
 * 属性预览
 */
class SimpleEffectActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEffectBinding.inflate(LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //方向
        binding.rgOrientation.setOnCheckedChangeListener { _, _ ->
            if (binding.rbOrientationHorizontal.isChecked) {
                binding.cardView.setOrientation(HoleCardView.ORIENTATION_HORIZONTAL)
            } else {
                binding.cardView.setOrientation(HoleCardView.ORIENTATION_VERTICAL)
            }
        }

        //卡片圆角
        binding.swRadius.onChange {
            binding.tvRadius.text = "卡片圆角(hc_radius) [$it]"
            binding.cardView.setCardRadius(dp2px(it))
        }

        //穿孔半径
        binding.swHoleRadius.onChange {
            binding.tvHoleRadius.text = "穿孔圆半径(hc_hole_radius) [$it]"
            binding.cardView.setHoleRadius(dp2px(it))
        }

        //开始方向
        binding.rgStartSide.setOnCheckedChangeListener { _, _ ->
            if (binding.rbSideStart.isChecked) {
                binding.cardView.setStartSide(HoleCardView.SIDE_START)
            } else {
                binding.cardView.setStartSide(HoleCardView.SIDE_END)
            }
        }

        //孔线偏移
        binding.swOffset.onChange {
            binding.tvOffset.text = "位置偏移(hc_offset) [$it]"
            binding.cardView.setPositionOffset(dp2px(it))
        }

        //穿孔偏移
        binding.swHoleOffset.onChange {
            binding.tvHoleOffset.text = "穿孔偏移(hc_hole_offset) [$it]"
            binding.cardView.setHoleOffset(dp2px(it))
        }

        //开始孔偏移
        binding.swHoleStartMargin.onChange {
            binding.tvHoleStartMargin.text = "开始孔线偏移(hc_dash_margin_start) [$it]"
            binding.cardView.setDashMarginStart(dp2px(it))
        }

        //结束孔偏移
        binding.swHoleEndMargin.onChange {
            binding.tvHoleEndMargin.text = "结束孔线偏移(hc_dash_margin_end) [$it]"
            binding.cardView.setDashMarginEnd(dp2px(it))
        }

        //是否显示虚线
        binding.cbDash.setOnCheckedChangeListener { _, isChecked ->
            binding.cardView.setDashVisible(isChecked)
        }

        //虚线半径
        binding.swDashRadius.onChange {
            binding.tvDashRadius.text = "虚线半径(hc_dash_radius) [$it]"
            binding.cardView.setDashRadius(dp2px(it))
        }

        //虚线间隙
        binding.swDashGap.onChange {
            binding.tvDashGap.text = "虚线间隙(hc_dash_gap) [$it]"
            binding.cardView.setDashGap(dp2px(it))
        }


    }


    private fun SeekBar.onChange(onProgressChange: (progress: Int) -> Unit) {
        this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onProgressChange(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }


    private fun dp2px(dp: Int): Int {
        return (dp * this.resources.displayMetrics.density + 0.5f).toInt()
    }

}