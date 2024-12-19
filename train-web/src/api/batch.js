import request from "@/util/request.js";

/**
 * 添加定时任务开始
 */
export const BatchAddService =
  (data) => request.post("batch/admin/job/add", data)
/**
 * 暂停定时任务开始
 */
export const BatchPauseService =
  (data) => request.post("batch/admin/job/pause", data)
/**
 * 重启定时任务开始
 */
export const BatchResumeService =
  (data) => request.post("batch/admin/job/resume", data)
/**
 * 重置定时任务开始
 */
export const BatchRescheduleService =
  (data) => request.post("batch/admin/job/reschedule", data)
/**
 * 删除定时任务开始
 */
export const BatchDeleteService =
  (data) => request.post("batch/admin/job/delete", data)
/**
 * 执行一次定时任务开始
 */
export const BatchRunService =
  (data) => request.post("batch/admin/job/run", data)

/**
 * 查看所有定时任务开始
 */
export const BatchQueryService =
  () => request.get("batch/admin/job/query")
