package com.hl.yun.chapter5

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hl.yun.R

class RightFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(
            R.layout.right_fragment, container,
            false
        )
    }

    companion object {
        const val TAG = "RightFragment"
    }

    //绑定 Fragment 和关联的 Activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")

    }
    //创建 Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    //确认 Activity 创建完毕
    //《第一行代码》第三版 这里用的是 onActivityCreated() 现已弃用
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

    }

    //Activity 能看到
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

    }

    //Activity 能交互
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    //暂停 不能交互
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    //停止 看不见
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    //Fragment 视图没了
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    //Activity 被销毁
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    //Activity 和 Fragment 取消绑定
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }


}